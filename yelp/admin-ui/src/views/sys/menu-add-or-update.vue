<template>
  <el-dialog v-model="visible" :title="!dataForm.id ? 'ADD' : 'EDIT'" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="dataForm" :rules="rules" ref="dataFormRef" @keyup.enter="dataFormSubmitHandle()" label-width="120px">
      <el-form-item prop="menuType" label="type">
        <el-radio-group v-model="dataForm.menuType" :disabled="!!dataForm.id">
          <el-radio :label="0">菜单</el-radio>
          <el-radio :label="1">按钮</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="name" label="name">
        <el-input v-model="dataForm.name" placeholder="name"></el-input>
      </el-form-item>
      <el-form-item prop="parentName" label="upper menu" class="menu-list">
        <el-popover ref="menuListPopover" placement="bottom-start" trigger="click" :width="400" popper-class="popover-pop">
          <template v-slot:reference>
            <el-input v-model="dataForm.parentName" :readonly="true" placeholder="upper menu">
              <template v-slot:suffix>
                <el-icon v-if="dataForm.pid !== '0'" @click.stop="deptListTreeSetDefaultHandle()" class="el-input__icon"><circle-close /></el-icon
              ></template>
            </el-input>
          </template>
          <div class="popover-pop-body">
            <el-tree :data="menuList" :props="{ label: 'name', children: 'children' }" node-key="id" ref="menuListTree" :highlight-current="true" :expand-on-click-node="false" accordion @current-change="menuListTreeCurrentChangeHandle"> </el-tree>
          </div>
        </el-popover>
      </el-form-item>
      <el-form-item v-if="dataForm.menuType === 0" prop="url" label="Route">
        <el-input v-model="dataForm.url" placeholder="Route"></el-input>
      </el-form-item>
      <el-form-item prop="sort" label="Sort">
        <el-input-number v-model="dataForm.sort" controls-position="right" :min="0" label="Sort"></el-input-number>
      </el-form-item>
      <el-form-item v-if="dataForm.menuType === 0" prop="openStyle" label="OpenMethod">
        <el-radio-group v-model="dataForm.openStyle">
          <el-radio :label="0">内部打开</el-radio>
          <el-radio :label="1">外部打开</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="permissions" label="authorization logo">
        <el-input v-model="dataForm.permissions" placeholder="multiple separated by commas, such as: sys:menu:save, sys:menu:update"></el-input>
      </el-form-item>
      <el-form-item v-if="dataForm.menuType === 0" prop="icon" label="icon" icon" class="icon-list">
        <el-popover ref="iconListPopover" placement="top-start" trigger="click" popper-class="popover-pop mod-sys__menu-icon-popover">
          <template v-slot:reference> <el-input v-model="dataForm.icon" :readonly="true" placeholder="Icon"></el-input></template>
          <div class="mod-sys__menu-icon-inner">
            <div class="mod-sys__menu-icon-list">
              <el-button v-for="(item, index) in iconList" :key="index" @click="iconListCurrentChangeHandle(item)" :class="{ 'is-active': dataForm.icon === item }">
                <svg class="icon-svg" aria-hidden="true"><use :xlink:href="`#${item}`"></use></svg>
              </el-button>
            </div>
          </div>
        </el-popover>
      </el-form-item>
    </el-form>
    <template v-slot:footer>
      <el-button @click="visible = false">CANCEL</el-button>
      <el-button type="primary" @click="dataFormSubmitHandle()">CONFIRM</el-button>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { reactive, ref } from "vue";
import baseService from "@/service/baseService";
import { getIconList } from "@/utils/utils";
import { IObject } from "@/types/interface";
import { ElMessage } from "element-plus";

const emit = defineEmits(["refreshDataList"]);

const visible = ref(false);
const menuList = ref([]);
const iconList = ref<string[]>([]);
const dataFormRef = ref();
const menuListTree = ref();
const menuListPopover = ref();
const iconListPopover = ref();

const dataForm = reactive({
  id: "",
  menuType: 0,
  name: "",
  pid: "0",
  parentName: "",
  url: "",
  permissions: "",
  sort: 0,
  icon: "",
  openStyle: 0
});

const rules = ref({
  name: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }],
  parentName: [{ required: true, message: "The required fields cannot be blank", trigger: "change" }]
});

const init = (id: number) => {
  visible.value = true;
  dataForm.id = "";

  //Reset form data
  if (dataFormRef.value) {
    dataFormRef.value.resetFields();
  }

  iconList.value = getIconList();

  dataForm.pid = "0";
  dataForm.parentName = "Level 1 menu";

  getMenuList().then(() => {
    if (id) {
      getInfo(id);
    }
  });
};

const init2 = (row: IObject) => {
  visible.value = true;

  //Reset form data
  if (dataFormRef.value) {
    dataFormRef.value.resetFields();
  }

  iconList.value = getIconList();

  dataForm.id = "";
  dataForm.pid = row.id;
  dataForm.parentName = row.name;

  getMenuList();
};

//Get menu list
const getMenuList = () => {
  return baseService.get("/sys/menu/list?type=0").then((res) => {
    menuList.value = res.data;
  });
};

//Get information
const getInfo = (id: number) => {
  baseService.get(`/sys/menu/${id}`).then((res) => {
    Object.assign(dataForm, res.data);
    if (dataForm.pid === "0") {
      return deptListTreeSetDefaultHandle();
    }
    menuListTree.value.setCurrentKey(dataForm.pid);
  });
};

//The upper menu tree, 设置默认值
const deptListTreeSetDefaultHandle = () => {
  dataForm.pid = "0";
  dataForm.parentName = "Level 1 menu";
};

//The upper menu tree, 选中
const menuListTreeCurrentChangeHandle = (data: IObject) => {
  dataForm.pid = data.id;
  dataForm.parentName = data.name;
  menuListPopover.value.hide();
};

//icon, 选中
const iconListCurrentChangeHandle = (icon: string) => {
  dataForm.icon = icon;
  iconListPopover.value.hide();
};

//Form Submission
const dataFormSubmitHandle = () => {
  dataFormRef.value.validate((valid: boolean) => {
    if (!valid) {
      return false;
    }
    (!dataForm.id ? baseService.post : baseService.put)("/sys/menu", dataForm).then((res) => {
      ElMessage.success({
        message: "SUCCESS",
        duration: 500,
        onClose: () => {
          visible.value = false;
          emit("refreshDataList");
        }
      });
    });
  });
};

defineExpose({
  init,
  init2
});
</script>

<style lang="less">
.el-popover.el-popper {
  overflow-x: hidden;
}
.mod-sys__menu {
  .menu-list,
  .icon-list {
    .el-input__inner,
    .el-input__suffix {
      cursor: pointer;
    }
  }
  &-icon-popover {
    width: 458px !important;
    overflow-y: hidden !important;
  }
  &-icon-inner {
    width: 100%;
    max-height: 260px;
    overflow-x: hidden;
    overflow-y: auto;
  }
  &-icon-list {
    width: 458px !important;
    padding: 0;
    margin: -8px 0 0 -8px;
    > .el-button {
      padding: 8px;
      margin: 8px 0 0 8px;
      > span {
        display: inline-block;
        vertical-align: middle;
        width: 18px;
        height: 18px;
        font-size: 18px;
      }
    }
  }
}
</style>
