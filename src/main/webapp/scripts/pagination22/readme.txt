官网
https://github.com/gbirke/jquery_pagination

jQuery Pagination插件
参考资料：https://github.com/gbirke/jquery_pagination

jquery paginate插件可以达到我们的要求，使用如下

使用：

1.首先定义一个页码容器

<div id="Pagination"></div>

2.设置属性

$("#Pagination").pagination(122, {
items_per_page:20,
callback:handlePaginationClick
});

3.定义一个相应的回调函数

function handlePaginationClick(new_page_index, pagination_container) {        //new_page_index:页码，从0开始， 

pagination_container：容器对象    return false;}这样就定制了一个简单的分页控件，如需更多功能，请参照如下属性：

callback：回调函数，就是点击页码除法的js事件，如上面定义的handlePaginationClick函数，可以通过ajax读取数据，或者控制数据的显隐

current_page：当前页码

items_per_page：每页的条数，用于计算总页数

link_to：当callback回调函数return true时页码会转到此处定义的链接，其中我们可以用__id__传入页码值

num_display_entries：展示页码的总数，默认为11，如果设置为0则简单的只显示“前一页，后一页”

next_text：下一页文本

next_show_always：是否总显示下一页

prev_text：前一页

prev_show_always：是否总显示前一页

num_edge_entries：显示最前几条，最后几条

load_first_page：初始化插件时是否调用回调函数


#我们还可以通过代码调用分页功能：
$("#News-Pagination").trigger('setPage', [4]);
// Go to the next page
$("#News-Pagination").trigger('nextPage');
// Go to the previous page
$("#News-Pagination").trigger('prevPage');