//存放主要交互逻辑js
//javascript 模块化
var seckill = {
	// 封装秒杀相关ajax的url
	URL : {

	},
	// 验证手机号
	validatePhone : function(phone) {
		if (phone && phone.length == 11 && !isNaN(phone)) {
			return true;
		} else {
			return false;
		}
	},
	// 详情页秒杀逻辑
	detail : {
		// 详情页初始化
		init : function(params) {
			// 手机验证和登录,计时交互
			// 规划交互流程
			var killPhone = $.cookie('killPhone');
			// 验证手机号
			if (!seckill.validatePhone(killPhone)) {
				// 绑定手机 控制输出
				var killPhoneModal = $('#killPhoneModal');
				killPhoneModal.modal({
					show : true,// 显示弹出层
					backdrop : false,// 禁止位置关闭
					keyboard : false
				// 关闭键盘事件
				});
				$('#killPhoneBtn')
				.click(function() {
					var inputPhone = $('#killPhoneKey').val();
						if (seckill.validatePhone(inputPhone)) {
							$.cookie('killPhone', inputPhone, {
								expires : 1 , path:'/seckill'
							});
								window.location.reload();
						} else {
							$('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误!</label>').show(300);
					}
				});
			}
			// 已经登录
		}
	}
}