<script lang="ts">
import SvgIcon from "@/components/base/svg-icon";
import baseService from "@/service/baseService";
import { useFullscreen } from "@vueuse/core";
import { defineComponent } from "vue";
import { useRouter } from "vue-router";
import { useAppStore } from "@/store";
import userLogo from "@/assets/images/user.png";
import "@/assets/css/header.less";
import { ElMessageBox } from "element-plus";

interface IExpand {
  userName?: string;
}

/**
 * Top right expansion area
 */
export default defineComponent({
  name: "Expand",
  components: { SvgIcon },
  props: {
    userName: String
  },
  setup(props: IExpand) {
    const router = useRouter();
    const store = useAppStore();
    const { isFullscreen, toggle } = useFullscreen();

    const onClickUserMenus = (path: string) => {
      if (path === "/login") {
        ElMessageBox.confirm("CONFIRM [LOGOUT] PROCESS?", "WARNING", {
          confirmButtonText: "CONFIRM",
          cancelButtonText: "CANCEL",
          type: "warning"
        })
          .then(() => {
            baseService.post("/logout").finally(() => {
              router.push(path);
            });
          })
          .catch(() => {
            //
          });
      } else {
        router.push(path);
      }
    };
    return {
      props,
      store,
      isFullscreen,
      userLogo,
      onClickUserMenus,
      toggle
    };
  }
});
</script>
<template>
  <div class="rr-header-right-items">
    <div @click="toggle" class="hidden-xs-only">
      <span>
        <svg-icon :name="isFullscreen ? 'tuichuquanping' : 'fullscreen2'"></svg-icon>
      </span>
    </div>
    <div style="display: flex; justify-content: center; align-items: center">
      <img :src="userLogo" :alt="props.userName" style="width: 30px; height: 30px; border-radius: 50%; margin-top: 3px; margin-right: 5px" />
      <el-dropdown @command="onClickUserMenus">
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item icon="lock" command="/user/password"> CHANGE PASSWORD </el-dropdown-item>
            <el-dropdown-item icon="switch-button" divided command="/login"> LOGOUT </el-dropdown-item>
          </el-dropdown-menu>
        </template>
        <span class="el-dropdown-link" style="display: flex">
          {{ props.userName }}
          <el-icon class="el-icon--right" style="font-size: 14px"><arrow-down /></el-icon>
        </span>
      </el-dropdown>
    </div>
  </div>
</template>
