/**
 * Created by BOBO on 2017/10/11.
 */


	/**
	 * 处理lemon云平台本地存储
	 * @param key
	 * @param value
	 * @param storageType
	 */
	//把数据保持到缓存中
	function setStorage(key,value,storageType) {
		var storage = storageType || sessionStorage;
		var value = JSON.stringify(value);
		storage.setItem(key, value);
	}
	
	//获取缓存中的数据
	function getStorage(key,storageType) {
		var storage = storageType || sessionStorage;
		var valueString = storage.getItem(key);
		if(valueString) {
			var valueObject = JSON.parse(valueString);
			return valueObject;
		}else {
			return null;
		}
	}
	
	//删除缓存中的数据
	function removeStorage(key,storageType) {
		var storage = storageType || sessionStorage;
		if(key) {
			storage.removeItem(key);
		}else {
			storage.clear();
		}
	}
	
	//获取某条具体的数据
	function getDataByStorage(storageName,index){
		var data = getStorage(storageName);
		if(data && data.rows && data.rows[index]){
			return data.rows[index];
		}else{
			return null;
		}
	}
	//树转数组，（入口方法）
	function getListByTree(tree){
		var listData = [];
		return treeToList(tree,listData,0);
		
	}
	//树转数组
	function treeToList(tree,listData,level){
		for(var i=0; i<tree.length;i++){
			var tem_childern_data = tree[i].list;
			tem_tree = tree[i];
			tem_tree.list = {};
			tem_tree.level = level;
			listData.push(tem_tree);
			if(tem_childern_data.length > 0){
				treeToList(tem_childern_data,listData,level+1);
			}
			
		}
		return listData;
	}
	
	//jstree的公共方法
	function jsTree_getSelectedOperationIds(class_name){
		var treeResult = $(class_name).jstree(true).get_checked([]);
		var new_treeData = '';
		for(var i=0;i<treeResult.length;i++){
			if(treeResult[i].state.disabled == false){
				var id = treeResult[i].id;
				var type = id.substring(0,1);
				if(type == 'o'){
					if(new_treeData){
						new_treeData += ','+id.substring(2);
					}else{ 
						new_treeData = id.substring(2);
					}
				}
			}
		}
		return new_treeData;
	}
	function jsTree_getSelectedNodes(class_name){
		var treeResult = $(class_name).jstree(true).get_checked([]);
		var new_treeData = '';
		for(var i=0;i<treeResult.length;i++){
			var id = treeResult[i].id;
			if(new_treeData){
				new_treeData += ','+id;
			}else{ 
				new_treeData = id;
			}
		}
		return new_treeData;
	}
	function createJsTreeItem(id,pid,name,is_selected,not_disable){
		var tem_item = {};
		tem_item.id = id;
		if(pid <= 0){
			tem_item.parent = '#';
		}else{
			tem_item.parent = pid;	
		}
		tem_item.text = name;
		tem_item.state = {
			'opened' : true,
		};
		if(is_selected){
			tem_item.state.selected = true;
		}else{
			tem_item.state.selected = false;
		}
		if(not_disable){
			tem_item.state.disabled = true;
		}else{
			tem_item.state.disabled = false;
		}
		return tem_item;
	}
	function selectJsTree(old_jsTree,new_jsTree){
		for(var i=0;i<new_jsTree.length;i++){
			for(var j=0;j<old_jsTree.length;j++){
				if(old_jsTree[j].id == new_jsTree[i].id){
					old_jsTree[j].state.selected = new_jsTree[i].state.selected;
					old_jsTree[j].state.disabled = new_jsTree[i].state.disabled;
				}
			}
		}
		return old_jsTree;
	}
	//时间戳转换
	Date.prototype.format = function(format) {  
	       var date = {  
	              "M+": this.getMonth() + 1,  
	              "d+": this.getDate(),  
	              "h+": this.getHours(),  
	              "m+": this.getMinutes(),  
	              "s+": this.getSeconds(),  
	              "q+": Math.floor((this.getMonth() + 3) / 3),  
	              "S+": this.getMilliseconds()  
	       };  
	       if (/(y+)/i.test(format)) {  
	              format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));  
	       }  
	       for (var k in date) {  
	              if (new RegExp("(" + k + ")").test(format)) {  
	                     format = format.replace(RegExp.$1, RegExp.$1.length == 1  
	                            ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));  
	              }  
	       }  
	       return format;  
	}  