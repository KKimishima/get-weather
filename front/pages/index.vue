<template>
  <div>
    <!--      時計-->
    <clock class="mb-2" />

    <b-card-group deck>
      <b-card title="本社" img-src="~/assets/本社.jpeg" img-alt="Image" img-top>
        <b-card-text>
          <b-table :dark="true" stacked :items="ibarakiFactory" />
        </b-card-text>
      </b-card>

      <b-card
        title="茨城工場"
        img-src="~/assets/茨城工場.jpg"
        img-alt="Image"
        img-top
        img-height="120%"
      >
        <b-card-text>
          <b-table :dark="true" stacked :items="tokyoOffice" />
        </b-card-text>
      </b-card>

      <b-card
        title="白河工場"
        img-src="~/assets/白河工場.jpg"
        img-alt="Image"
        img-top
        img-height="120%"
      >
        <b-card-text>
          <b-table :dark="true" stacked :items="shirakawaFactory" />
        </b-card-text>
      </b-card>

      <b-card
        title="大阪営業所"
        img-src="~/assets/大阪営業所.jpg"
        img-alt="Image"
        img-top
        tag="article"
      >
        <b-card-text>
          <b-table :dark="true" stacked :items="osakaOffice" />
        </b-card-text>
      </b-card>

      <b-card
        title="大阪工場"
        img-src="~/assets/大阪工場.png"
        img-alt="Image"
        img-top
        tag="article"
        img-height="40%"
      >
        <b-card-text>
          <b-table :dark="true" stacked :items="osakaFactory" />
        </b-card-text>
      </b-card>
    </b-card-group>
  </div>
</template>

<script>
import Clock from '~/components/Clock.vue'
const findRegionWeather = (data, region) => {
  return data
    .filter((obj) => obj.region === region)
    .map((obj) => ({
      天気: obj.condition,
      温度: obj.temp,
      最高気温: obj.temp_max,
      最低気温: obj.temp_min,
      測定時間: obj.time
    }))
}
export default {
  components: {
    Clock
  },
  async asyncData({ app }) {
    const response = await app.$axios.get('/api/v1/weather')
    return { weatherDate: response.data.weather }
  },
  data() {
    return {}
  },
  mounted() {
    setInterval(() => this.getDate(), 1000 * 60 * 60)
  },
  methods: {
    async getDate() {
      console.log('get')
      const res = await this.$axios.get('/api/v1/weather')
      this.weatherDate = res.data.weather
    }
  },
  computed: {
    ibarakiFactory() {
      return findRegionWeather(this.weatherDate, '茨城工場')
    },
    tokyoOffice() {
      return findRegionWeather(this.weatherDate, '本社')
    },
    shirakawaFactory() {
      return findRegionWeather(this.weatherDate, '白河工場')
    },
    osakaOffice() {
      return findRegionWeather(this.weatherDate, '大阪営業所')
    },
    osakaFactory() {
      return findRegionWeather(this.weatherDate, '大阪工場')
    }
  }
}
</script>

<style></style>
