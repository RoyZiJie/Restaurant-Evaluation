<template>
  <el-dialog v-model="visible" :title="!dataForm.id ? 'ADD' : 'EDIT'" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="dataForm" :rules="rules" ref="dataFormRef" @keyup.enter="dataFormSubmitHandle()" label-width="120px">
      <el-form-item prop="name" label="name">
        <el-input v-model="dataForm.name" placeholder="name"></el-input>
      </el-form-item>
      <el-form-item prop="parentName" label="Superior department" class="dept-list">
        <el-popover :width="400" ref="deptListPopover" placement="bottom-start" trigger="click" popper-class="popover-pop">
          <template v-slot:reference>
            <el-input v-model="dataForm.parentName" :readonly="true" placeholder="Superior Department">
              <template v-slot:suffix>
                <el-icon v-if="user.superAdmin === 1 && dataForm.pid !== '0'" @click.stop="deptListTreeSetDefaultHandle()" class="el-input__icon"><circle-close /></el-icon>
              </template> </el-input
          ></template>
          <div class="popover-pop-body"><el-tree :data="deptList" :props="{ label: 'name', children: 'children' }" node-key="id" ref="deptListTree" :highlight-current="true" :expand-on-click-node="false" accordion @current-change="deptListTreeCurrentChangeHandle"> </el-tree></div>
        </el-popover>
      </el-form-item>
      <el-form-item prop="sort" label="Sort">
        <el-input-number v-model="dataForm.sort" controls-position="right" :min="0" label="Sort"></el-input-number>
      </el-form-item>
    </el-form>
    <template v-slot:footer>
      <el-button @click="visible = false">CANCEL</el-button>
      <el-button type="primary" @click="dataFormSubmitHandle()">CONFIRM</el-button>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { computed, reactive, ref } from "vue";
import baseService from "@/service/baseService";
import { IObject } from "@/types/interface";
import { useAppStore } from "@/store";
import { ElMessage } from "element-plus";

const store = useAppStore();

const emit = defineEmits(["refreshDataList"]);
const visible = ref(false);
const dataFormRef = ref();
const deptList = ref([]);
const deptListPopover = ref();
const deptListTree = ref();

const dataForm = reactive({
  id: "",
  name: "",
  pid: "",
  parentName: "",
  sort: 0
});

const user = computed(() => store.state.user);

const rules = ref({
  name: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }],
  parentName: [{ required: true, message: "The required fields cannot be blank", trigger: "change" }]
});

const init = (id?: number) => {
  visible.value = true;
  dataForm.id = "";

  //Reset form data
  if (dataFormRef.value) {
    dataFormRef.value.resetFields();
  }

  getDeptList().then(() => {
    if (id) {
      getInfo(id);
    } else if (store.state.user.superAdmin === 1) {
      deptListTreeSetDefaultHandle();
    }
  });
};

//Get a department list
const getDeptList = () => {
  return baseService.get("/sys/dept/list").then((res) => {
    if (res.code !== 0) {
      return ElMessage.error(res.msg);
    }
    deptList.value = res.data;
  });
};

//Get information
const getInfo = (id: number) => {
  baseService.get(`/sys/dept/${id}`).then((res) => {
    Object.assign(dataForm, res.data);

    if (dataForm.pid === "0") {
      return deptListTreeSetDefaultHandle();
    }
    deptListTree.value.setCurrentKey(dataForm.pid);
  });
};

//Superior department tree, 设置默认值
const deptListTreeSetDefaultHandle = () => {
  dataForm.pid = "0";
  dataForm.parentName = "Level 1 department";
};

//Superior department tree, 选中
const deptListTreeCurrentChangeHandle = (data: IObject) => {
  dataForm.pid = data.id;
  dataForm.parentName = data.name;
  deptListPopover.value.hide();
};

//Form Submission
const dataFormSubmitHandle = () => {
  dataFormRef.value.validate((valid: boolean) => {
    if (!valid) {
      return false;
    }
    (!dataForm.id ? baseService.post : baseService.put)("/sys/dept", dataForm).then((res) => {
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
  init
});
</script>

<style lang="less" scoped>
.mod-sys__dept {
  .dept-list {
    .el-input__inner,
    .el-input__suffix {
      cursor: pointer;
    }
  }
}
</style>
