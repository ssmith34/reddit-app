<template>
  <div>
      <span>Subreddit:</span> <span><input v-model='subreddit' type='text'/></span>
      <button v-on:click='getData'>See what's trending!</button>

      <div v-for="post in apiData" v-bind:key="post.id">
          Title: {{post.title}} <br>
          # of Likes: {{post.ups}} <br>
          <img v-bind:src = "post.image"/>
      </div>
  </div>

</template>

<script>
import apiService from '../services/apiService.js';
export default {
    data() {
        return {
            apiData : [],
            subreddit : ''
        }
    }, 
    methods : {
    getData() {
        apiService.getData(this.subreddit).then(
            (response) => {
                this.apiData.length = 0;
                this.apiData = response.data;
            }

        );
        this.cleanUpImageLink();
    },
    cleanUpImageLink() {
        for (let i=0; i < this.apiData.length; i++) {
            this.apiData[i].image = 'hi';
        }
    }
    }
}
</script>

<style>
    img {
        width: 300px;
    }
</style>