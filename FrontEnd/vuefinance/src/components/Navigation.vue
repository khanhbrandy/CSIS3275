<template>
    <div>
        <div class="topnav">
            <a class="active" href="/">Home</a>
            <a href="/register">Register</a>
            <a href="/transaction">Transactions</a>
            <a href="/report">Reports</a>
            <a href ="/goals">Goals</a>
        </div>
    </div>
</template>


<script>

export default {
    name: 'navBar',
    data() {
        return {

        }
    },
    methods: {
    }

}
</script>

<style scoped>
/* Add a black background color to the top navigation */
.topnav {
  background-color: #333;
  overflow: hidden;
}

/* Style the links inside the navigation bar */
.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

/* Change the color of links on hover */
.topnav a:hover {
  background-color: #ddd;
  color: black;
}

/* Add a color to the active/current link */
.topnav a.active {
  background-color: #006381;
  color: white;
}
</style>
