import Vuex from 'vuex';
import Vue from 'vue';
import axios from "axios";
import { resolve } from 'url';

Vue.use(Vuex);

const state = {
    isLoggedIn: false,
    status: {}
}

export default new Vuex.Store({
    state,
    mutations: {
        setIsLoggedIn(state, payload) {
            state.isLoggedIn = payload;
        },
        setStatus(state, payload) {
            state.status = payload;
        }
    },
    actions: {
        login({ commit }, payload) {
            console.log("action", payload);
            return new Promise((resolve, reject) => {
                axios({
                    url: "/login",
                    data: payload,
                    method: "POST"
                }).then(resp => {
                    console.log(resp);
                    if (resp.data.result) commit("setIsLoggedIn", true);
                    resolve(resp.data.result);
                }).catch(err => {
                    console.log(err);
                    reject(err);
                })
            })
        },
        isLoggedIn({ commit }) {
            return new Promise((resolve, reject) => {
                axios({
                    url: "/state",
                    method: "POST"
                }).then(resp => {
                    console.log(resp);
                    if (resp.data.result) commit("setIsLoggedIn", true);
                    resolve(resp.data.result);
                }).catch(err => {
                    console.log(err);
                    reject(err);
                })
            })
        },
        getStatus({ commit }) {
            return new Promise((resolve, reject) => {
                axios({
                    url: "/status",
                    method: "POST"
                }).then(resp => {
                    console.log(resp);
                    if (resp.data.result) {
                        commit("setStatus", resp.data);
                        resolve(resp.data.result);
                    } else {
                        commit("setStatus", {});
                        resolve(resp.data.result);
                    }
                }).catch(err => {
                    console.log(err);
                    reject(err);
                })
            });
        },
        logout({ commit }) {
            return new Promise((resolve, reject) => {
                axios({
                    url: "/logout",
                    method: "GET"
                }).then(resp => {
                    console.log(resp);
                    commit("setIsLoggedIn", false);
                    commit("setStatus", {});
                    resolve(resp.data.result);
                }).catch(err => {
                    console.log(err);
                    reject(err);
                })
            });
        }
    }
})