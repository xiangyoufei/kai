var baseUrl ='http://114.215.175.196:8080/';

import axios from 'axios'
Vue.prototype.$axios = axios    //全局注册，使用方法为:this.$axios
Vue.prototype.qs = qs           //全局注册，使用方法为:this.qs

function postRequest(resource,json) { //定义回调函数
    axios({
        method: 'POST',
        url: baseUrl+resource,
        headers: {
            token: localStorage.getItem('token') || axios.token //放自己的token
        },
        data: json
    }).then(res=>{
        // back(res) //回调函数
        console.log(123)
        return res;
    }).catch(err=>{
        console.log(err)
    })
}
function getRequest(resource,json) { //定义回调函数
    axios({
        method: 'GET',
        url: baseUrl+'?'+resource,
        headers: {
            token: localStorage.getItem('token') || axios.token //放自己的token
        },
        data: json
    }).then(res=>{
        // back(res) //回调函数
        console.log(123)
        return res;
    }).catch(err=>{
        console.log(err)
    })
}

export default {
    relationship  //抛出去
}

//json字符串转json
function toJson(jsonString){
    for (var i in jsonString){
        if(typeof jsonString[i]=='string'){
            try{
                var obj = JSON.parse(jsonString[i])
                jsonString[i] = obj
                toJson(jsonString[i])
            }
            catch(e){
                return
            }
        }else if(typeof jsonString[i]=='object'){
            toJson(jsonString[i])
        }
    }
    return jsonString
}