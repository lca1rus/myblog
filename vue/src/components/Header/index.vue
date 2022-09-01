<template>
  <div>
      <!-- <div class="container">
        <div class="login-wrapper"> -->
            <div class="header">Login</div>
           
       
    <!--     </div>
    </div> -->
  </div>
</template>

<script>
export default {

}
</script>

<style>
* {
    padding: 0;
    margin: 0;
    font-family: 'Open Sans Light';
    letter-spacing: .05em;
}
html {
    height: 100%;
}

body {
    height: 100%;
}

 .header {
    font-size:  30px;
    font-weight: bold;
    text-align: center;
    line-height: 200px;
}

.input-item {
    display: block;
    width: 100%;
    margin-bottom: 20px;
    border: none;
    padding: 10px;
    border-bottom: 2px solid rgb(128,125,125);
    font-size: 15px;
    outline: none;
}

.input-item::placeholder {
    text-transform: uppercase;
}

.btn {
    text-align: center;
    padding: 5px;
    margin-top: 40px;
    background-image: linear-gradient(to right,#fbc2eb,#a6c1ee);;
    color: #fff;
}


</style>