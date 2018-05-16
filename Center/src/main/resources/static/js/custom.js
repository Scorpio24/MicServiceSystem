/**
 * Created by bwang.abcft on 2018/1/17.
 */


(function ($) {
    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        // deleteEmptyProperty(o);
        return o;
    };
})(jQuery)


function deleteEmptyProperty(object) {
    for (var i in object) {
        var value = object[i];
        if (typeof value === 'object') {
            if (Array.isArray(value)) {
                if (value.length == 0) {
                    delete object[i];
                    continue;
                }
            }
            deleteEmptyProperty(value);
            if (isEmpty(value)) {
                delete object[i];
            }
        } else {
            if (value === '' || value === null || value === undefined) {
                delete object[i];
            } else {
            }
        }
    }
}

function isEmpty(obj) {
    if (obj === null || obj === undefined) {
        return true;
    }
    if (obj instanceof String) {
        if (obj.length === 0) {
            return true;
        }
    }
    return false;
}


var _global_menu_fun = {
    /**
     * 之定义打开一个tap
     */
    openTapUrl: function (dataUrl, dataIndex, menuName) {
        this.menuItem(null, dataUrl, dataIndex, menuName);
    },
    menuItem: function (object, dataUrl, dataIndex, menuName) {
        var that = this;
        if (!dataUrl) {
            dataUrl = $(this).attr('href');
        }

        if (!dataIndex) {
            dataIndex = $(this).data('index');
        }
        if (!menuName) {
            menuName = $.trim($(this).text());
        }
        // 获取标识数据
        var flag = true;

        if (dataUrl == undefined || $.trim(dataUrl).length == 0)return false;

        // 选项卡菜单已存在
        $('.J_menuTab',parent.document).each(function () {
            if ($(this).data('id') == dataUrl) {
                if (!$(this).hasClass('active')) {
                    $(this).addClass('active').siblings('.J_menuTab').removeClass('active');
                    that.scrollToTab(this);
                    // 显示tab对应的内容区
                    $('.J_mainContent .J_iframe').each(function () {
                        if ($(this).data('id') == dataUrl) {
                            $(this).show().siblings('.J_iframe').hide();
                            return false;
                        }
                    });
                }
                flag = false;
                return false;
            }
        });

        // 选项卡菜单不存在
        if (flag) {
            var str = '<a href="javascript:;" class="active J_menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
            $('.J_menuTab',parent.document).removeClass('active');
            // 添加选项卡对应的iframe
            var str1 = '<iframe class="J_iframe" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
            $('.J_mainContent',parent.document).find('iframe.J_iframe').hide().parents('.J_mainContent').append(str1);

            //显示loading提示
//            var loading = layer.load();
//
//            $('.J_mainContent iframe:visible').load(function () {
//                //iframe加载完成后隐藏loading提示
//                layer.close(loading);
//            });
            // 添加选项卡
            $('.J_menuTabs .page-tabs-content',parent.document).append(str);

            that.scrollToTab($('.J_menuTab.active',parent.document));
        }
        return false;
    },
    //滚动到指定选项卡
    scrollToTab: function (element) {
        var that = this;
        var marginLeftVal = that.calSumWidth($(element).prevAll()), marginRightVal = that.calSumWidth($(element).nextAll());
        // 可视区域非tab宽度
        var tabOuterWidth = that.calSumWidth($(".content-tabs",parent.document).children().not(".J_menuTabs"));
        //可视区域tab宽度
        var visibleWidth = $(".content-tabs",parent.document).outerWidth(true) - tabOuterWidth;
        //实际滚动宽度
        var scrollVal = 0;
        if ($(".page-tabs-content",parent.document).outerWidth() < visibleWidth) {
            scrollVal = 0;
        } else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - $(element).next().outerWidth(true))) {
            if ((visibleWidth - $(element).next().outerWidth(true)) > marginRightVal) {
                scrollVal = marginLeftVal;
                var tabElement = element;
                while ((scrollVal - $(tabElement).outerWidth()) > ($(".page-tabs-content",parent.document).outerWidth() - visibleWidth)) {
                    scrollVal -= $(tabElement).prev().outerWidth();
                    tabElement = $(tabElement).prev();
                }
            }
        } else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - $(element).prev().outerWidth(true))) {
            scrollVal = marginLeftVal - $(element).prev().outerWidth(true);
        }
        $('.page-tabs-content',parent.document).animate({
            marginLeft: 0 - scrollVal + 'px'
        }, "fast");
    },
    calSumWidth: function (elements) {
        var width = 0;
        $(elements).each(function () {
            width += $(this).outerWidth(true);
        });
        return width;
    }


}