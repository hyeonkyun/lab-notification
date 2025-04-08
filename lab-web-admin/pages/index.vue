<template>
  <div>
    {{ name }}
  </div>
</template>

<script setup lang="ts">
const name = ref<string>('hello world');
</script>

<style scoped>
</style>