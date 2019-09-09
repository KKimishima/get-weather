<template>
  <div>
    <span class="display-4">
      {{ year }}/{{ month }}/{{ day }} {{ hours }}:{{ minutes }}:{{
        seconds
      }}
      ({{ week }})
    </span>
  </div>
</template>

<script>
const zeroPadding = (num, digit) => {
  return (Array(digit).join('0') + num).slice(-digit)
}
const weekJP = (date) => {
  return ['日', '月', '火', '水', '木', '金', '土'][date - 1]
}
export default {
  data() {
    return {
      date: new Date()
    }
  },
  computed: {
    year() {
      return this.date.getFullYear()
    },
    month() {
      return zeroPadding(this.date.getMonth() + 1, 2)
    },
    day() {
      return zeroPadding(this.date.getDate(), 2)
    },
    hours() {
      return zeroPadding(this.date.getHours(), 2)
    },
    minutes() {
      return zeroPadding(this.date.getMinutes(), 2)
    },
    seconds() {
      return zeroPadding(this.date.getSeconds(), 2)
    },
    week() {
      return weekJP(this.date.getDate())
    }
  },
  mounted() {
    this.setDate()
    setInterval(() => this.setDate(), 1000)
  },
  methods: {
    setDate() {
      this.date = new Date()
    }
  }
}
</script>

<style scoped></style>
