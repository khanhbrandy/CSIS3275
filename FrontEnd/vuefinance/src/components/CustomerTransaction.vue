<template>
    <div>
        <h1>Transaction</h1>
    </div>
</template>

<script>
export default {
    name: "customerTransaction",
    data() {
        return {
            customer: null,


        }
    },
    methods: {
        retrieveCustomer() {
            const id = localStorage.getItem('cid');
            console.log("CID: "+ id)
        }
    },

  mounted() {
    this.retrieveCustomer();

  }
}
</script>

<style>

</style>