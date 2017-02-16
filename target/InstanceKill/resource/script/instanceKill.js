//存放主要交互逻辑js代码
//javascript模块化
var instanceKill = {
	//封装秒杀相关的URL
	URL:{
		now:function(){
			return '/instanceKill/time/now';
		},
		exposer:function(instanceKillId){
			return '/instanceKill/'+instanceKillId+'/exposer';
		},
		execution:function(instanceKillId,md5){
			return '/instanceKill/'+instanceKillId+'/'+md5+'/execution';
		}
	},
	//获取秒杀地址，控制实现逻辑，执行秒杀
	handleInstanceKill:function(instanceKillId,node){
		node.hide()
			.html('<button class="btn btn-primary btn-lg" id="instanceKillBtn">开始秒杀</button>');//按钮
		$.post(instanceKill.URL.exposer(instanceKillId),{},function(result){
			if(result&&result['success']){
				var exposer = result['data'];
				if(exposer['exposed']){
					//开启秒杀
					//获取秒杀地址
					var md5 = exposer['md5'];
					var killUrl = instanceKill.URL.execution(instanceKillId, md5);
					console.log("killUrl:"+killUrl);
					//绑定一次点击事件，一次触发后就接触绑定
					$('#instanceKillBtn').one('click',function(){
						//执行秒杀请求
						//1：先禁用按钮
						$(this).addClass('disabled');
						//2:发送秒杀请求执行秒杀
						$.post(killUrl,{},function(result){
							if(result&&result['success']){
								var killResult = result['data'];
								var state = killResult['state'];
								var stateInfo = killResult['stateInfo'];
								//3：显示秒杀结果
								node.html('<span class="label label-success">'+stateInfo+'</span>')
							}
						})
					});
					node.show();
				}else{
					//客户端电脑在运行足够久时可能每台电脑计时有偏差
					//未开启秒杀
					var now = exposer['now'];
					var start = exposer['start'];
					var end = exposer['end'];
					instanceKill.countdown(instanceKillId, now, start, end);
				}
			}else{
				console.log("result:"+result);
			}
		})
	},
	//验证手机号
	validatePhone:function(phone){
		if(phone&&phone.length==11&&!isNaN(phone)){
			return true;
		}else{
			return false;
		}
	},
	countdown:function(instanceKillId,nowTime,startTime,endTime){
		var instanceKillBox = $('#instanceKill-box');
		if(nowTime>endTime){
			//秒杀结束
			instanceKillBox.html('秒杀结束！');
		}else if(nowTime<startTime){
			//秒杀未开始，计时
			var instanceKillTime = new Date(startTime+1000);
			instanceKillBox.countdown(instanceKillTime,function(event){
				//时间格式
				var format = event.strftime('秒杀倒计时： %D天  %H时  %M分  %S秒');
				instanceKillBox.html(format);
			//时间完成后事件
			}).on('finish.countdown',function(){
				instanceKill.handleInstanceKill(instanceKillId,instanceKillBox);
			})
		}else {
			//秒杀开始
			instanceKill.handleInstanceKill(instanceKillId,instanceKillBox);
		}
	},
	detail:{
		//详情页初始化
		init:function(params){
			//手机验证和登陆，计时交互
			//规划我们的交互流程
			//在cookie中查找手机号
			var instanceKillPhone = $.cookie('instanceKillPhone');
			//验证手机号
			if(!instanceKill.validatePhone(instanceKillPhone)){
				//绑定phone
				//控制输出
				var instanceKillPhoneModal = $('#instanceKillPhoneModal');
				instanceKillPhoneModal.modal({
					//显示弹出层
					show:true,
					backdrop:'static',//禁止位置关闭
					keyboard:false,//关闭键盘事件
				})
				$('#instanceKillPhoneBtn').click(function(){
					var inputKillPhone = $('#instanceKillPhoneKey').val();
					if(instanceKill.validatePhone(inputKillPhone)){
						//电话写入cookie
						$.cookie('instanceKillPhone',inputKillPhone,{expires:7,path:'/instanceKill'});
						//刷新页面
						window.location.reload();
					}else{
						$('#instanceKillPhoneMessage').hide().html('<label class="label label-danger">手机号错误！</label>').show(300);
					}
				});
			}
			//已经登陆
			//计时交互
			//Ajax请求
			var startTime = params['startTime'];
			var endTime = params['endTime'];
			var instanceKillId = params['instanceKillId']
			$.get(instanceKill.URL.now(),{},function(result){
				if(result&&result['success']){
					var nowTime = result['data'];
					//时间判断，计时交互
					instanceKill.countdown(instanceKillId, nowTime, startTime, endTime);
				}else{
					console.log("result:"+result);
				}
			})
		}
	},
}