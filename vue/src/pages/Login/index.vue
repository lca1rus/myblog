<template>
  <div>
            <form action="/user" method="post">
                <input id="user" type="text" name="username" placeholder="username" class="input-item">
                <input id="password" type="password" name="password" placeholder="password" class="input-item">
<!--                    <input value="REST-DELETE 提交" type="submit"/>-->
                <input id="login" class="btn" value="Login" type="submit">
                </form>
                <span id="re"></span>
  </div>
</template>

<script>
export default {
name:'Login',
methods: {

},
}
</script>

<style>

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
#login{
   width: 100px;
   margin-left: 70px;
}
</style>