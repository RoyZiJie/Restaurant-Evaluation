<template>
  <el-dialog v-model="visible" title="Upload file" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-upload :action="url" :file-list="fileList" drag multiple :before-upload="beforeUploadHandle" :on-success="successHandle" class="text-center">
      <el-icon class="el-icon--upload"><upload-filled /></el-icon>
      <div class="el-upload__text">将文件拖到此处，或点击上传</div>
      <template v-slot:tip>
        <div class="el-upload__tip">Only support jpg, png, and gif format files!</div>
      </template>
    </el-upload>
  </el-dialog>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import { getToken } from "@/utils/cache";
import { IObject } from "@/types/interface";
import app from "@/constants/app";
import { ElMessage } from "element-plus";
const emit = defineEmits(["refreshDataList"]);

const visible = ref(false);
const url = ref("");
const num = ref(0);
const fileList = ref<IObject[]>();

const init = () => {
  visible.value = true;
  url.value = `${app.api}/sys/oss/upload?token=${getToken()}`;
  num.value = 0;
  fileList.value = [];
};

//Before uploading
const beforeUploadHandle = (file: IObject) => {
  if (file.type !== "image/jpg" && file.type !== "image/jpeg" && file.type !== "image/png" && file.type !== "image/gif") {
    ElMessage.error("Only support jpg, png, and gif format files!");
    return false;
  }
  num.value++;
};

//UploadSUCCESS
const successHandle = (res: IObject, file: IObject, list: IObject[]) => {
  if (res.code !== 0) {
    return ElMessage.error(res.msg);
  }

  fileList.value = list;
  num.value--;
  if (num.value === 0) {
    ElMessage.success({
      message: "SUCCESS",
      duration: 500,
      onClose: () => {
        visible.value = false;
        emit("refreshDataList");
      }
    });
  }
};

defineExpose({
  init
});
</script>
