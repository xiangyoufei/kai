var vm = new Vue({
    el: '.beautiy-template',
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
        articles: [
            {
                id: 'Runoob1',
                title: '第一篇文章标题',
                content: '第一篇文章正文',
                summary: '第一篇文章摘要',
                images: '第一篇文章图片'
            },
            {
                id: 'Runoob2',
                title: '第2篇文章标题',
                content: '第2篇文章正文',
                summary: '第2篇文章摘要',
                images: '第2篇文章图片'
            },
            {
                id: 'Runoob3',
                title: '第3篇文章标题',
                content: '第3篇文章正文',
                summary: '第3篇文章摘要',
                images: '第3篇文章图片'
            },
            {
                id: 'Runoob4',
                title: '第4篇文章标题',
                content: '第4篇文章正文',
                summary: '第4篇文章摘要',
                images: '第4篇文章图片'
            },
            {
                id: 'Runoob5',
                title: '第5篇文章标题',
                content: '第5篇文章正文',
                summary: '第5篇文章摘要',
                images: '第5篇文章图片'
            }, {
                id: 'Runoob6',
                title: '第6篇文章标题',
                content: '第6篇文章正文',
                summary: '第6篇文章摘要',
                images: '第6篇文章图片'
            }, {
                id: 'Runoob7',
                title: '第7篇文章标题',
                content: '第7篇文章正文',
                summary: '第7篇文章摘要',
                images: '第7篇文章图片'
            }
        ]
    }
})