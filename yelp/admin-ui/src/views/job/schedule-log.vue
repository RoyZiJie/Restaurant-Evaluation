<template>
  <el-dialog v-model="visible" title="Log List" :close-on-click-modal="false" :close-on-press-escape="false" width="75%">
    <el-form :inline="true" :model="state.dataForm" @keyup.enter="state.getDataList()">
      <el-form-item>
        <el-input v-model="state.dataForm.jobId" placeholder="Task ID" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="state.getDataList()">SEARCH</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="state.dataListLoading" :data="state.dataList" border @sort-change="state.dataListSortChangeHandle" height="460" style="width: 100%">
      <el-table-column prop="jobId" label="Task ID" header-align="center" align="center" width="180"></el-table-column>
      <el-table-column prop="beanName" label="bean name" header-align="center" align="center"></el-table-column>
      <el-table-column prop="params" label="parameters" header-align="center" align="center"></el-table-column>
      <el-table-column prop="status" label="STATUS" header-align="center" align="center">
        <template v-slot="scope">
          <el-tag v-if="scope.row.status === 1" size="small">SUCCESS</el-tag>
          <el-tag v-else type="danger" size="small" @click="showErrorInfo(scope.row.id)" style="cursor: pointer">FAILED</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="times" label="Time to take (unit: milliseconds)" header-align="center" align="center"></el-table-column>
      <el-table-column prop="createDate" label="Execution time" header-align="center" align="center" width="180"></el-table-column>
    </el-table>
    <el-pagination :current-page="state.page" :page-sizes="[10, 20, 50, 100]" :page-size="state.limit" :total="state.total" layout="total, sizes, prev, pager, next, jumper" @size-change="state.pageSizeChangeHandle" @current-change="state.pageCurrentChangeHandle"> </el-pagination>
  </el-dialog>
</template>

<script lang="ts" setup>
import useView from "@/hooks/useView";
import { reactive, ref, toRefs } from "vue";
import baseService from "@/service/baseService";
import { ElMessageBox } from "element-plus";

const visible = ref(false);

const view = reactive({
  getDataListURL: "/sys/scheduleLog/page",
  getDataListIsPage: true,
  dataForm: {
    jobId: ""
  }
});

const state = reactive({ ...useView(view), ...toRefs(view) });

const init = () => {
  visible.value = true;
  state.getDataList();
};

//FAILED Information
const showErrorInfo = (id: string) => {
  baseService.get(`/sys/scheduleLog/${id}`).then((res) => {
    ElMessageBox.alert(res.data.error);
  });
};

defineExpose({
  init
});
</script>
