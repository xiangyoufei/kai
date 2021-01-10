var vm = new Vue({
    el: '.index-template',
    data: {
        articleTypes: {
            Art:"art",
            Issue:"issue",
            Fashion:"fashion",
            Photography:"photography",
            Video:"video",
            About:"about",
            Submisstion:"submisstion",
        },
        langs: [
            {name:"中文"},
            {name:"English"},
            {name:"Italiano"}
        ],
        sites: [
            {name: 'Runoob'},
            {name: 'Google'},
            {name: 'Taobao'}
        ]
    }
})