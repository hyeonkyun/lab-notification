<template>
  <NuxtLayout>
    <p>Current color: {{ color }} , Current counter : {{ counter }}</p>
    <NuxtPage />
  </NuxtLayout>
</template>
<script setup lang="ts">
const counter = useCounter();
const color = useColor(); // Same as useState('color')
</script>
