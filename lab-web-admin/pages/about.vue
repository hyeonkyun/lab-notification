<template>
  <q-page padding>
    <div class="q-my-xl text-center">
      <PageTitle title="About" />
      <PageDescription
        description="Vue Master Course 웹은 Nuxt3로 만들어졌습니다."
      />

      <div class="column">
        <!-- <RouterLink to="/">RouterLink Home </RouterLink>
        <RouterLink to="https://www.youtube.com/@gymcoding">
          RouterLink Youtube
        </RouterLink>
        <br />
        <NuxtLink to="/" prefetched-class="bg-yellow" :prefetch="false">
          NuxtLink Home
        </NuxtLink>
        <NuxtLink to="/course" prefetched-class="bg-yellow" no-prefetch>
          NuxtLink Course
        </NuxtLink>
        <NuxtLink
          to="https://www.youtube.com/@gymcoding"
          prefetched-class="bg-yellow"
        >
          NuxtLink Youtube
        </NuxtLink> -->

        <div class="q-gutter-y-sm q-mt-md">
          <div class="text-subtitle1 text-weight-bold">useCounterStore()</div>
          <div>{{ count }}</div>
          <div>{{ doubleCount }}</div>
          <div>
            <q-btn label="increment" @click="counterStore.increment()" />
          </div>
        </div>
      </div>
    </div>
  </q-page>
</template>

<script setup lang="ts">
import { storeToRefs } from 'pinia';
import { useCounterStore } from '~/stores/counter';
const counterStore = useCounterStore();
const { count, doubleCount } = storeToRefs(counterStore);
</script>
