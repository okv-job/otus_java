<template>
  <div class="login-container">
    <form method="post" @submit.prevent="auth">
        <h1>Login</h1>
        <input type="text" v-model="login" placeholder="Login">
        <input type="password" v-model="password" placeholder="Password">
        <input type="submit" value="login" class="btn">
    </form>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      login: "",
      password: ""
    };
  },
  methods: {
    auth() {
      console.log(this.login, this.password);
      this.$store
        .dispatch("login", {
          login: this.login,
          password: this.password
        })
        .then(result => {
          if (result) this.$router.push("/status");
          else alert("Wrong login or password");
        })
        .catch(err => alert("Errrrrrr"));
    }
  },
  mounted() {
    this.$store.dispatch("isLoggedIn").then(res => {
      if (res) this.$router.push("/status");
    });
  }
};
</script>

<style>
.login-container,
.login-container > form {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  margin: auto;
}

form {
  background: whitesmoke;
  align-self: center;
}

input[type="text"],
input[type="password"] {
  padding: 5px;
  font-size: 1.2em;
  margin: 10px;
}
</style>
