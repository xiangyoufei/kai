var vm = new Vue({
    el: '.index-template',
    data: {
        articleTypes: {
            Art: "art",
            Issue: "issue",
            Fashion: "fashion",
            Photography: "photography",
            Video: "video",
            About: "about",
            Submisstion: "submisstion",
        },
        sites: [
            {name: 'Runoob'},
            {name: 'Google'},
            {name: 'Taobao'}
        ]
    },
    mounted() {
        this.data.sites = getRequest("index");
        console(this.data.sites)
    }
})