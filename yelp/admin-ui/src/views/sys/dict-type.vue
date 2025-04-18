<template>
  <div class="mod-sys__dict">
    <el-form :inline="true" :model="state.dataForm" @keyup.enter="state.getDataList()">
      <el-form-item>
        <el-input v-model="state.dataForm.dictName" placeholder="Dictionary name" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="state.dataForm.dictType" placeholder="Dictionary Type" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="state.getDataList()">SEARCH</el-button>
      </el-form-item>
      <el-form-item>
        <el-button v-if="state.hasPermission('sys:dict:save')" type="primary" @click="addOrUpdateHandle()">ADD</el-button>
      </el-form-item>
      <el-form-item>
        <el-button v-if="state.hasPermission('sys:dict:delete')" type="danger" @click="state.deleteHandle()">DELETE</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="state.dataListLoading" :data="state.dataList" border @selection-change="state.dataListSelectionChangeHandle" @sort-change="state.dataListSortChangeHandle" style="width: 100%">
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="dictName" label="Dictionary name" header-align="center" align="center"></el-table-column>
      <el-table-column prop="dictType" label="Dictionary type" header-align="center" align="center">
        <template v-slot="scope">
          <el-button type="primary" link @click="showTypeList(scope.row)">{{ scope.row.dictType }}</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="sort" label="Sort" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="remark" label="Note" header-align="center" align="center"></el-table-column>
      <el-table-column prop="createDate" label="CREATETIME" sortable="custom" header-align="center" align="center" width="180"></el-table-column>
      <el-table-column label="PROCESS" fixed="right" header-align="center" align="center" width="180">
        <template v-slot="scope">
          <el-button type="primary" link @click="showTypeList(scope.row)">字典配置</el-button>
          <el-button v-if="state.hasPermission('sys:dict:update')" type="primary" link @click="addOrUpdateHandle(scope.row.id)">EDIT</el-button>
          <el-button v-if="state.hasPermission('sys:dict:delete')" type="primary" link @click="state.deleteHandle(scope.row.id)">DELETE</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination :current-page="state.page" :page-sizes="[10, 20, 50, 100]" :page-size="state.limit" :total="state.total" layout="total, sizes, prev, pager, next, jumper" @size-change="state.pageSizeChangeHandle" @current-change="state.pageCurrentChangeHandle"> </el-pagination>
    <!-- 弹窗, ADD / EDIT -->
    <add-or-update ref="addOrUpdateRef" @refreshDataList="state.getDataList"></add-or-update>
    <!-- 字典类型数据 -->
    <el-drawer v-if="dictDataVisible" v-model="dictDataVisible" :title="state.focusDictTypeTitle" :size="800" :close-on-press-escape="false" class="rr-drawer"> <DictTypeList :dictTypeId="state.focusDictTypeId"></DictTypeList></el-drawer>
  </div>
</template>

<script lang="ts" setup>
import useView from "@/hooks/useView";
import { reactive, ref, toRefs } from "vue";
import AddOrUpdate from "./dict-type-add-or-update.vue";
import DictTypeList from "./dict-data.vue";
import { IObject } from "@/types/interface";
import { useRoute } from "vue-router";
const route = useRoute();

const dictDataVisible = ref(false);

const view = reactive({
  getDataListURL: "/sys/dict/type/page",
  getDataListIsPage: true,
  deleteURL: "/sys/dict/type",
  deleteIsBatch: true,
  dataForm: {
    id: "0",
    dictName: "",
    dictType: ""
  },
  focusDictTypeId: "",
  focusDictTypeTitle: ""
});

const state = reactive({ ...useView(view), ...toRefs(view) });

const showTypeList = (row: IObject) => {
  dictDataVisible.value = true;
  state.focusDictTypeId = row.id;
  state.focusDictTypeTitle = `${route.meta.title} - ${row.dictType}`;
};

const addOrUpdateRef = ref();
const addOrUpdateHandle = (id?: number) => {
  addOrUpdateRef.value.init(id);
};
</script>
