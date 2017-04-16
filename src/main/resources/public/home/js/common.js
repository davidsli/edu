var sina={$:function(objName){if(document.getElementById){return eval('document.getElementById("'+objName+'")')}else{return eval('document.all.'+objName)}},isIE:navigator.appVersion.indexOf("MSIE")!=-1?true:false,addEvent:function(l,i,I){if(l.attachEvent){l.attachEvent("on"+i,I)}else{l.addEventListener(i,I,false)}},delEvent:function(l,i,I){if(l.detachEvent){l.detachEvent("on"+i,I)}else{l.removeEventListener(i,I,false)}},readCookie:function(O){var o="",l=O+"=";if(document.cookie.length>0){var i=document.cookie.indexOf(l);if(i!=-1){i+=l.length;var I=document.cookie.indexOf(";",i);if(I==-1)I=document.cookie.length;o=unescape(document.cookie.substring(i,I))}};return o},writeCookie:function(i,l,o,c){var O="",I="";if(o!=null){O=new Date((new Date).getTime()+o*3600000);O="; expires="+O.toGMTString()};if(c!=null){I=";domain="+c};document.cookie=i+"="+escape(l)+O+I},readStyle:function(I,l){if(I.style[l]){return I.style[l]}else if(I.currentStyle){return I.currentStyle[l]}else if(document.defaultView&&document.defaultView.getComputedStyle){var i=document.defaultView.getComputedStyle(I,null);return i.getPropertyValue(l)}else{return null}}};

//焦点图构造函数 071221 mengjia
//FocusPic(BigPicID,SmallPicsID,TitleID,MemoID) 大图容器ID，小图列表容器ID，标题容器ID ,说明容器ID
//	add(BigPic,SmallPic,Url,Title) 大图地址，小图地址，链接地址，标题，说明文字
//	begin() 开始执行
//	TimeOut = 5000 默认切换时间
var FocusPic=function(BigPicID,SmallPicsID,TitleID,MemoID,width,height){this.Data=[];this.ImgLoad=[];this.TimeOut=5000;var isIE=navigator.appVersion.indexOf("MSIE")!=-1?true:false;this.width=width;this.height=height;this.adNum=0;var TimeOutObj;if(!FocusPic.childs){FocusPic.childs=[]};this.showTime=null;this.showSum=10;this.ID=FocusPic.childs.push(this)-1;this.Add=function(BigPic,SmallPic,Title,Url,Memo){var ls;this.Data.push([BigPic,SmallPic,Title,Url,Memo]);ls=this.ImgLoad.length;this.ImgLoad.push(new Image);this.ImgLoad[ls].src=BigPic};this.TimeOutBegin=function(){clearInterval(TimeOutObj);TimeOutObj=setInterval("FocusPic.childs["+this.ID+"].next()",this.TimeOut)};this.TimeOutEnd=function(){clearInterval(TimeOutObj)};this.select=function(num){if(num>this.Data.length-1){return};if(num==this.adNum){return};this.TimeOutBegin();if(BigPicID){if(this.$(BigPicID)){var aObj=this.$(BigPicID).getElementsByTagName("a")[0];aObj.href=this.Data[num][2];if(this.aImgY){this.aImgY.style.display='none';this.aImg.style.zIndex=0};this.aImgY=this.$('F'+this.ID+'BF'+this.adNum);this.aImg=this.$('F'+this.ID+'BF'+num);clearTimeout(this.showTime);this.showSum=10;this.showTime=setTimeout("FocusPic.childs["+this.ID+"].show()",50)}};if(TitleID){if(this.$(TitleID)){this.$(TitleID).innerHTML="<a href=\""+this.Data[num][2]+"\" target=\"_blank\">"+this.Data[num][3]+"</a>"}};if(MemoID){if(this.$(MemoID)){this.$(MemoID).innerHTML=this.Data[num][4]}};if(SmallPicsID){if(this.$(SmallPicsID)){var sImg=this.$(SmallPicsID).getElementsByTagName("span"),i;for(i=0;i<sImg.length;i++){if(i==num||num==(i-this.Data.length)){sImg[i].className="selected"}else{sImg[i].className=""}}}};if(this.onselect){this.onselect()};this.adNum=num};this.show=function(){this.showSum--;this.aImgY.style.display='block';this.aImg.style.display='block';if(isIE){this.aImg.style.filter="alpha(opacity=0)";this.aImg.style.filter="alpha(opacity="+(10-this.showSum)*10+")"}else{this.aImg.style.opacity=0;this.aImg.style.opacity=(10-this.showSum)*0.1};if(this.showSum<=0){this.aImgY.style.display='none';this.aImg.style.zIndex=0;this.aImgY=null}else{this.aImg.style.zIndex=2;this.showTime=setTimeout("FocusPic.childs["+this.ID+"].show()",50)}};this.next=function(){var temp=this.adNum;temp++;if(temp>=this.Data.length){temp=0};this.select(temp)};this.MInStopEvent=function(ObjID){if(ObjID){if(this.$(ObjID)){if(this.$(ObjID).attachEvent){this.$(ObjID).attachEvent("onmouseover",Function("FocusPic.childs["+this.ID+"].TimeOutEnd()"));this.$(ObjID).attachEvent("onmouseout",Function("FocusPic.childs["+this.ID+"].TimeOutBegin()"))}else{this.$(ObjID).addEventListener("mouseover",Function("FocusPic.childs["+this.ID+"].TimeOutEnd()"),false);this.$(ObjID).addEventListener("mouseout",Function("FocusPic.childs["+this.ID+"].TimeOutBegin()"),false)}}}};this.begin=function(){this.MInStopEvent(TitleID);this.MInStopEvent(SmallPicsID);this.MInStopEvent(BigPicID);this.adNum=0;var i,temp="";if(BigPicID){if(this.$(BigPicID)){var aObj=this.$(BigPicID).getElementsByTagName("a")[0];aObj.style.zoom=1;this.$(BigPicID).style.position="relative";this.$(BigPicID).style.zoom=1;this.$(BigPicID).style.overflow="hidden";for(i=0;i<this.Data.length;i++){temp+='<img src="'+this.Data[i][0]+'" id="F'+this.ID+'BF'+i+'" style="display:'+(i==this.adNum?'block':'none')+'" galleryimg="no"'+(this.width?' width="'+this.width+'"':'')+(this.height?' height="'+this.height+'"':'')+' alt="'+this.Data[i][3]+'" />'};aObj.innerHTML=temp;var imgObjs=aObj.getElementsByTagName("img");for(i=0;i<imgObjs.length;i++){imgObjs[i].style.position="relative";imgObjs[i].style.top=0;imgObjs[i].style.left=0;}}};if(SmallPicsID){if(this.$(SmallPicsID)){temp="";for(i=0;i<this.Data.length;i++){temp+="<span"+(this.adNum==i?' class="selected"':"")+"><a href=\""+this.Data[i][2]+"\" target=\"_blank\"><img src=\""+this.Data[i][1]+"\" onmouseover=\"FocusPic.childs["+this.ID+"].select("+i+")\" alt=\""+this.Data[i][3]+"\" /></a></span>"};this.$(SmallPicsID).innerHTML=temp}};if(TitleID){if(this.$(TitleID)){this.$(TitleID).innerHTML="<a href=\""+this.Data[this.adNum][2]+"\" target=\"_blank\">"+this.Data[this.adNum][3]+"</a>"}};if(MemoID){if(this.$(MemoID)){this.$(MemoID).innerHTML=this.Data[this.adNum][4]}};this.TimeOutBegin()};this.$=function(objName){if(document.getElementById){return eval('document.getElementById("'+objName+'")')}else{return eval('document.all.'+objName)}}};

//舌签构造函数
function SubShowClass(C,i,c,l,I){var V=this,v=V;V.parentObj=V.$(C);if(V.parentObj==null&&C!="none"){throw new Error("SubShowClass(ID)参数错误:ID 对像不存在!(value:"+C+")")};V.lock=false;V.label=[];V.defaultID=c==null?0:c;V.selectedIndex=V.defaultID;V.openClassName=l==null?"selected":l;V.closeClassName=I==null?"":I;V.mouseIn=false;var O=function(){v.mouseIn=true},o=function(){v.mouseIn=false};if(C!="none"&&C!=""){if(V.parentObj.attachEvent){V.parentObj.attachEvent("onmouseover",O)}else{V.parentObj.addEventListener("mouseover",O,false)}};if(C!="none"&&C!=""){if(V.parentObj.attachEvent){V.parentObj.attachEvent("onmouseout",o)}else{V.parentObj.addEventListener("mouseout",o,false)}};if(typeof(i)!="string"){i="onmousedown"};i=i.toLowerCase();switch(i){case "onmouseover":V.eventType="mouseover";break;case "onmouseout":V.eventType="mouseout";break;case "onclick":V.eventType="click";break;case "onmouseup":V.eventType="mouseup";break;default:V.eventType="mousedown"};V.autoPlay=false;V.autoPlayTimeObj=null;V.spaceTime=5000};SubShowClass.prototype={version:"1.31",author:"mengjia",_setClassName:function(l,I){var o=this,i;i=l.className;if(i){i=i.replace(o.openClassName,"");i=i.replace(o.closeClassName,"");i+=" "+(I=="open"?o.openClassName:o.closeClassName)}else{i=(I=="open"?o.openClassName:o.closeClassName)};l.className=i},addLabel:function(labelID,contID,parentBg,springEvent,blurEvent){var t=this,labelObj=this.$(labelID),contObj=this.$(contID);if(labelObj==null&&labelID!="none"){throw new Error("addLabel(labelID)参数错误:labelID 对像不存在!(value:"+labelID+")")};var TempID=this.label.length;if(parentBg==""){parentBg=null};this.label.push([labelID,contID,parentBg,springEvent,blurEvent]);var tempFunc=function(){t.select(TempID)};if(labelID!="none"){if(labelObj.attachEvent){labelObj.attachEvent("on"+this.eventType,tempFunc)}else{labelObj.addEventListener(this.eventType,tempFunc,false)}};if(TempID==this.defaultID){if(labelID!="none"){this._setClassName(labelObj,"open")};if(this.$(contID)){contObj.style.display=""};if(this.ID!="none"){if(parentBg!=null){this.parentObj.style.background=parentBg}};if(springEvent!=null){eval(springEvent)}}else{if(labelID!="none"){this._setClassName(labelObj,"close")};if(contObj){contObj.style.display="none"}};var mouseInFunc=function(){t.mouseIn=true},mouseOutFunc=function(){t.mouseIn=false};if(contObj){if(contObj.attachEvent){contObj.attachEvent("onmouseover",mouseInFunc)}else{contObj.addEventListener("mouseover",mouseInFunc,false)};if(contObj.attachEvent){contObj.attachEvent("onmouseout",mouseOutFunc)}else{contObj.addEventListener("mouseout",mouseOutFunc,false)}}},select:function(num,force){if(typeof(num)!="number"){throw new Error("select(num)参数错误:num 不是 number 类型!(value:"+num+")")};if(force!=true&&this.selectedIndex==num){return};var i;for(i=0;i<this.label.length;i++){if(i==num){if(this.label[i][0]!="none"){this._setClassName(this.$(this.label[i][0]),"open")};if(this.$(this.label[i][1])){this.$(this.label[i][1]).style.display=""};if(this.ID!="none"){if(this.label[i][2]!=null){this.parentObj.style.background=this.label[i][2]}};if(this.label[i][3]!=null){eval(this.label[i][3])}}else if(this.selectedIndex==i||force==true){if(this.label[i][0]!="none"){this._setClassName(this.$(this.label[i][0]),"close")};if(this.$(this.label[i][1])){this.$(this.label[i][1]).style.display="none"};if(this.label[i][4]!=null){eval(this.label[i][4])}}};this.selectedIndex=num},random:function(){var O=this;if(arguments.length!=O.label.length){throw new Error("random()参数错误:参数数量与标签数量不符!(length:"+arguments.length+")")};var l=0,o;for(o=0;o<arguments.length;o++){l+=arguments[o]};var I=Math.random(),i=0;for(o=0;o<arguments.length;o++){i+=arguments[o]/l;if(I<i){O.select(o);break}}},order:function(){var O=this;if(arguments.length!=O.label.length){throw new Error("order()参数错误:参数数量与标签数量不符!(length:"+arguments.length+")")};if(!(/^\d+$/).test(SubShowClass.sum)){return};var i=0,o;for(o=0;o<arguments.length;o++){i+=arguments[o]};var I=SubShowClass.sum%i;if(I==0){I=i};var l=0;for(o=0;o<arguments.length;o++){l+=arguments[o];if(l>=I){O.select(o);break}}},play:function(spTime){var t=this;if(typeof(spTime)=="number"){this.spaceTime=spTime};clearInterval(this.autoPlayTimeObj);this.autoPlayTimeObj=setInterval(function(){t.autoPlayFunc()},this.spaceTime);this.autoPlay=true},autoPlayFunc:function(){var i=this;if(i.autoPlay==false||i.mouseIn==true){return};i.nextLabel()},nextLabel:function(){var t=this,index=this.selectedIndex;index++;if(index>=this.label.length){index=0};this.select(index);if(this.autoPlay==true){clearInterval(this.autoPlayTimeObj);this.autoPlayTimeObj=setInterval(function(){t.autoPlayFunc()},this.spaceTime)}},previousLabel:function(){var t=this,index=this.selectedIndex;index--;if(index<0){index=this.label.length-1};this.select(index);if(this.autoPlay==true){clearInterval(this.autoPlayTimeObj);this.autoPlayTimeObj=setInterval(function(){t.autoPlayFunc()},this.spaceTime)}},stop:function(){var i=this;clearInterval(i.autoPlayTimeObj);i.autoPlay=false},$:function(objName){if(document.getElementById){return eval('document.getElementById("'+objName+'")')}else{return eval('document.all.'+objName)}}};SubShowClass.readCookie=function(O){var o="",l=O+"=";if(document.cookie.length>0){var i=document.cookie.indexOf(l);if(i!=-1){i+=l.length;var I=document.cookie.indexOf(";",i);if(I==-1)I=document.cookie.length;o=unescape(document.cookie.substring(i,I))}};return o};SubShowClass.writeCookie=function(i,l,o,c){var O="",I="";if(o!=null){O=new Date((new Date).getTime()+o*3600000);O="; expires="+O.toGMTString()};if(c!=null){I=";domain="+c};document.cookie=i+"="+escape(l)+O+I};SubShowClass.sum=SubShowClass.readCookie("SSCSum");if((/^\d+$/).test(SubShowClass.sum)){SubShowClass.sum++}else{SubShowClass.sum=1};SubShowClass.writeCookie("SSCSum",SubShowClass.sum,12);

var sina={$:function(objName){if(document.getElementById){return eval('document.getElementById("'+objName+'")')}else{return eval('document.all.'+objName)}},isIE:navigator.appVersion.indexOf("MSIE")!=-1?true:false,addEvent:function(l,i,I){if(l.attachEvent){l.attachEvent("on"+i,I)}else{l.addEventListener(i,I,false)}},delEvent:function(l,i,I){if(l.detachEvent){l.detachEvent("on"+i,I)}else{l.removeEventListener(i,I,false)}},readCookie:function(O){var o="",l=O+"=";if(document.cookie.length>0){var i=document.cookie.indexOf(l);if(i!=-1){i+=l.length;var I=document.cookie.indexOf(";",i);if(I==-1)I=document.cookie.length;o=unescape(document.cookie.substring(i,I))}};return o},writeCookie:function(i,l,o,c){var O="",I="";if(o!=null){O=new Date((new Date).getTime()+o*3600000);O="; expires="+O.toGMTString()};if(c!=null){I=";domain="+c};document.cookie=i+"="+escape(l)+O+I},readStyle:function(I,l){if(I.style[l]){return I.style[l]}else if(I.currentStyle){return I.currentStyle[l]}else if(document.defaultView&&document.defaultView.getComputedStyle){var i=document.defaultView.getComputedStyle(I,null);return i.getPropertyValue(l)}else{return null}}};

//滚动图片构造函数
eval(function(p,a,c,k,e,r){e=function(c){return(c<62?'':e(parseInt(c/62)))+((c=c%62)>35?String.fromCharCode(c+29):c.toString(36))};if('0'.replace(0,e)==0){while(c--)r[e(c)]=k[c];k=[function(e){return r[e]||e}];e=function(){return'([2-46-9a-df-hj-zA-Z]|[12]\\w)'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('6 1A(q,L,M,N,D){2.q=q;2.L=L;2.M=M;2.N=N;2.D=D;2.1l="dotItem";2.1m="dotItemOn";2.O=[];2.1B="onclick";2.u=v;2.12=0;2.o=0;2.13=10;2.h=10;2.p=E;2.g=0;2.14=v;2.1C=5;2.15;2.r;2.b="s";2.z=A.16("1n");2.9=A.16("1n");2.w=A.16("1n")};1A.prototype={version:"1.44",author:"mengjia",17:0,B:v,initialize:6(){f c=2;3(!2.q){1D 1E 1F("必须指定q.");d};2.4=2.$(2.q);3(!2.4){1D 1E 1F("q不是正确的对象.(q = \\""+2.q+"\\")");d};2.4.j[2.p?\'1G\':\'1H\']=2.o+"px";2.4.j.18="19";2.9.t=2.4.t;2.4.t="";2.4.1a(2.z);2.z.1a(2.9);3(2.u){2.z.1a(2.w);2.w.t=2.9.t};2.z.j.18="19";2.z.j.P="1";2.z.j[2.p?\'1G\':\'1H\']="32766px";2.9.j.18="19";2.9.j.P="1";2.w.j.18="19";2.w.j.P="1";3(!2.p){2.9.j.1I="1b";2.9.j.1J="1b"};2.9.j.P="1";3(2.u&&!2.p){2.w.j.1I="1b";2.w.j.1J="1b"};2.w.j.P="1";2.l(2.4,"mouseover",6(){c.1o()});2.l(2.4,"1p",6(){c.Q()});3(2.L){2.R=2.$(2.L);3(2.R){2.l(2.R,"1K",6(e){c.1L();e=e||1M;c.F(e)});2.l(2.R,"1N",6(){c.S()});2.l(2.R,"1p",6(){c.S()})}};3(2.M){2.T=2.$(2.M);3(2.T){2.l(2.T,"1K",6(e){c.1O();e=e||1M;c.F(e)});2.l(2.T,"1N",6(){c.U()});2.l(2.T,"1p",6(){c.U()})}};f 1q=C.ceil(2.9[2.p?\'1P\':\'1Q\']/2.o),i,m;2.17=1q;3(2.N){2.1c=2.$(2.N);2.1c.t="";3(2.1c){1R(i=0;i<1q;i++){m=A.16("span");2.1c.1a(m);2.O.push(m);3(i==2.g){m.1d=2.1m}8{m.1d=2.1l};3(2.D==\'number\'){m.t=i+1}8 3(1e(2.D)==\'string\'){m.t=2.D}8{m.t=\'\'};m.title="第"+(i+1)+"页";m.n=i;m[2.1B]=6(){c.G(2.n)}}}};2.4[2.p?\'1S\':\'1T\']=0;3(2.14){2.Q()};2.7=2.p?\'1S\':\'1T\';2.k=2.p?\'scrollHeight\':\'scrollWidth\';3(1e(2.1f)===\'6\'){2.1f()};2.1U()},1O:6(){3(2.b!="s"){d};f c=2;2.b="1g";H(2.r);2.1r();2.r=1s(6(){c.1r()},2.13)},1L:6(){3(2.b!="s"){d};f c=2;2.b="1g";H(2.r);2.1t();2.r=1s(6(){c.1t()},2.13)},1r:6(){3(2.u){3(2.4[2.7]+2.h>=2.9[2.k]){2.4[2.7]=2.4[2.7]+2.h-2.9[2.k]}8{2.4[2.7]+=2.h}}8{3(2.4[2.7]+2.h>=2.9[2.k]-2.o){2.4[2.7]=2.9[2.k]-2.o;2.U()}8{2.4[2.7]+=2.h}};2.V()},1t:6(){3(2.u){3(2.4[2.7]-2.h<=0){2.4[2.7]=2.9[2.k]+2.4[2.7]-2.h}8{2.4[2.7]-=2.h}}8{3(2.4[2.7]-2.h<=0){2.4[2.7]=0;2.S()}8{2.4[2.7]-=2.h}};2.V()},U:6(){3(2.b!="1g"&&2.b!=\'B\'){d};2.b="W";H(2.r);f I=2.12-2.4[2.7]%2.12;2.X(I)},S:6(){3(2.b!="1g"&&2.b!=\'B\'){d};2.b="W";H(2.r);f I=-2.4[2.7]%2.12;2.X(I)},X:6(n,1u){f c=2;f a=n/5;f 1h=E;3(!1u){3(a>2.h){a=2.h};3(a<-2.h){a=-2.h}};3(C.1v(a)<1&&a!=0){a=a>=0?1:-1}8{a=C.1V(a)};f temp=2.4[2.7]+a;3(a>0){3(2.u){3(2.4[2.7]+a>=2.9[2.k]){2.4[2.7]=2.4[2.7]+a-2.9[2.k]}8{2.4[2.7]+=a}}8{3(2.4[2.7]+a>=2.9[2.k]-2.o){2.4[2.7]=2.9[2.k]-2.o;2.b="s";1h=v}8{2.4[2.7]+=a}}}8{3(2.u){3(2.4[2.7]+a<0){2.4[2.7]=2.9[2.k]+2.4[2.7]+a}8{2.4[2.7]+=a}}8{3(2.4[2.7]-a<0){2.4[2.7]=0;2.b="s";1h=v}8{2.4[2.7]+=a}}};3(1h){d};n-=a;3(C.1v(n)==0){2.b="s";3(2.14){2.Q()};2.V();d}8{2.V();2.r=setTimeout(6(){c.X(n,1u)},2.13)}},pre:6(){3(2.b!="s"){d};2.b="W";2.G(2.g-1)},1W:6(1X){3(2.b!="s"){d};2.b="W";3(2.u){2.G(2.g+1)}8{3(2.4[2.7]>=2.9[2.k]-2.o){2.b="s";3(1X){2.G(0)}}8{2.G(2.g+1)}}},Q:6(){f c=2;3(!2.14){d};H(2.15);2.15=1s(6(){c.1W(v)},2.1C*1000)},1o:6(){H(2.15)},G:6(n){3(2.g==n){d};3(n<0){n=2.17-1};clearTimeout(2.r);2.b="W";f I=n*2.o-2.4[2.7];2.X(I,v)},V:6(){f g=C.1V(2.4[2.7]/2.o);3(g>=2.17){g=0};3(g==2.g){d};2.g=g;3(2.g>C.floor(2.9[2.p?\'1P\':\'1Q\']/2.o)){2.g=0};f i;1R(i=0;i<2.O.1Y;i++){3(i==2.g){2.O[i].1d=2.1m}8{2.O[i].1d=2.1l}};3(1e(2.1f)===\'6\'){2.1f()}},1i:0,1j:0,Y:\'ok\',1U:6(){3(1e(Z.ontouchstart)===\'undefined\'){d};3(!2.B){d};f 1k=2;2.l(2.4,\'touchstart\',6(e){1k.1Z(e)});2.l(2.4,\'touchmove\',6(e){1k.21(e)});2.l(2.4,\'touchend\',6(e){1k.1x(e)})},1Z:6(e){2.1o();2.1i=e.1y[0].22;2.23=Z.24;2.25=Z.26;2.27=2.4[2.7]},21:6(e){3(e.1y.1Y>1){2.1x()};2.1j=e.1y[0].22;f cX=2.1i-2.1j;3(2.Y==\'ok\'){3(2.25==Z.26&&2.23==Z.24&&C.1v(cX)>20){2.Y=\'B\'}8{d}};2.b=\'B\';f x=2.27+cX;3(x>=2.9[2.k]){x=x-2.9[2.k]};3(x<0){x=x+2.9[2.k]};2.4[2.7]=x;e.F()},1x:6(e){3(2.Y!=\'B\'){d};2.Y=\'ok\';f cX=2.1i-2.1j;3(cX<0){2.S()}8{2.U()};2.Q()},$:6(1z){3(A.28){d 29(\'A.28("\'+1z+\'")\')}8{d 29(\'A.all.\'+1z)}},isIE:navigator.appVersion.indexOf("MSIE")!=-1?v:E,l:6(y,J,K){3(y.2a){y.2a("on"+J,K)}8{y.addEventListener(J,K,E)}},delEvent:6(y,J,K){3(y.2c){y.2c("on"+J,K)}8{y.removeEventListener(J,K,E)}},F:6(e){3(e.F){e.F()}8{e.returnValue=E}}};',[],137,'||this|if|scDiv||function|_scroll|else|lDiv01|thisMove|_state|thisTemp|return||var|pageIndex|space||style|_sWidth|addEvent|tempObj|num|frameWidth|upright|scrollContId|_scrollTimeObj|ready|innerHTML|circularly|true|lDiv02|scrollNum|obj|stripDiv|document|touch|Math|listType|false|preventDefault|pageTo|clearInterval|fill|eventType|func|arrLeftId|arrRightId|dotListId|dotObjArr|zoom|play|alObj|rightEnd|arObj|leftEnd|accountPageIndex|stoping|move|iPadStatus|window|||pageWidth|speed|autoPlay|_autoTimeObj|createElement|pageLength|overflow|hidden|appendChild|left|dotListObj|className|typeof|onpagechange|floating|theEnd|iPadX|iPadLastX|tempThis|dotClassName|dotOnClassName|DIV|stop|mouseout|pages|moveLeft|setInterval|moveRight|quick|abs||_touchend|touches|objName|ScrollPic|listEvent|autoPlayTime|throw|new|Error|height|width|cssFloat|styleFloat|mousedown|rightMouseDown|event|mouseup|leftMouseDown|offsetHeight|offsetWidth|for|scrollTop|scrollLeft|iPad|round|next|reStar|length|_touchstart||_touchmove|pageX|iPadScrollX|pageXOffset|iPadScrollY|pageYOffset|scDivScrollLeft|getElementById|eval|attachEvent||detachEvent'.split('|'),0,{}))

//菜单
var sinaY = {
				$: function(objName) {
					if (document.getElementById) {return eval('document.getElementById("' + objName + '")')
					} else { return eval('document.all.' + objName)}}
			 };
			var PulldownMenu=function(Id){
				var self= sinaY.$(Id);
				var mItem=[];
				var timer;
				var openItemT, openItmeC;
				this.addLabel=function(menuT,menuC){
				  var menuT=sinaY.$(menuT);
				  var menuC=sinaY.$(menuC);
				 var Tween = {  
					           Quart: {
                  easeIn: function(t,b,c,d){
                          return c*(t/=d)*t*t*t + b;
                  },
                  easeOut: function(t,b,c,d){
                        return -c * ((t=t/d-1)*t*t*t - 1) + b;
                  },
                  easeInOut: function(t,b,c,d){
                        if ((t/=d/2) < 1) return c/2*t*t*t*t + b;
                        return -c/2 * ((t-=2)*t*t*t - 2) + b;
                  }
           }
    };
                  var setOpacity=function(obj,opacity){
                  if(document.documentElement.filters){   
             obj.style.filter="alpha(opacity="+opacity+")";
                  }else{       
                    obj.style.opacity=opacity/100;  

                  }
        };
                  var animate=function(obj,startVariable,changeValue,broadcastTime,fx){
                   var curTime=0;
                   var interVal=setInterval(function(){
                          if(curTime>=broadcastTime){
                            clearInterval(interVal);
                          }
                         for(var i in startVariable){
                        obj.style[i]=fx(curTime,startVariable[i],changeValue[i],broadcastTime)+"px";

                          setOpacity(obj,fx(curTime,startVariable[i],changeValue[i],broadcastTime));
                         }
                      curTime+=50;
                   },50)
        };
				var mClose=function(){

					//animate(menuC,{height:90},{height:-90},300,Tween.Quart.easeInOut);
					menuT.className="";
					menuC.style.display="none";
				  }
				  var mFunOpen=function(){
				  	clearTimeout(timer);
					if(openItmeC){ 

						//animate(openItmeC,{height:90},{height:-90},300,Tween.Quart.easeInOut);
						openItmeT.className = '';
						openItmeC.style.display = 'none';
					}
					menuT.className="selected";
					//animate(menuC,{height:0},{height:90},500,Tween.Quart.easeInOut);
					menuC.style.display="block";
					openItmeT = menuT;
					openItmeC = menuC;

				  }
				  var mcFunOpen=function(){
				   clearTimeout(timer);
				   menuC.style.display="block";
				  }
				  var mFunClose=function(){
				    timer=setTimeout(mClose,100);
				  }
				  if (menuT.attachEvent) {
						 	menuT.attachEvent("onmouseover", mFunOpen);
							menuC.attachEvent("onmouseover", mcFunOpen);
							menuT.attachEvent("onmouseout", mFunClose);
							menuC.attachEvent("onmouseout", mFunClose);
				 } else {
							menuT.addEventListener("mouseover",mFunOpen, false);
							menuC.addEventListener("mouseover",mcFunOpen, false);
							menuT.addEventListener("mouseout",mFunClose, false);
							menuC.addEventListener("mouseout",mFunClose, false);
						};

				} 

			};