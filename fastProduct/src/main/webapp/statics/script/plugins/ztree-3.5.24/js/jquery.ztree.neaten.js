
	var setting = {
			view: {
            	addHoverDom: null,						//移入节点时触发事件
            	removeHoverDom: null,					//移出节点时触发事件
            	selectedMulti: true,							//是否允许选中多个节点，按Ctrl时多选节点
            	dblClickExpand: false,							//双击时是否展开节点
            	expandSpeed: "fast",							//设置展开速度
            },
            check: {
                enable: false,							//是否显示复选框
                chkStyle: "checkbox",					//复选框类型，checkbox为多选，radio为单选
                nocheckInherit: true,					//新增子节点时是否继承父类
            },
            callback:{					
            	beforeRemove: beforeRemove,		//删除前执行事件
            	beforeDrop: zTreeBeforeDrop,	//拖拽前执行事件
            	beforeRename: beforeRename,		//编辑结束时捕获事件
            	onClick: zTreeOnClick			//单击节点事件
        	},
        	data: {
                simpleData: {
                    enable: true,			//设置树的数据模式，true为简单模式，false为复杂模式
                    idKey: "id",			
					pIdKey: "pId",			//父类id
					rootPId: 0				//最顶级父类的默认id
                },
            },
            edit: {
                enable: false,					//是否触发编辑状态
                drag: {
                	isCopy: false,				//是否复制节点
                	isMove: true,				//是否允许拖动节点
                },
                editNameSelectAll: true,		//点击编辑时是否为全选状态
                showRemoveBtn: true,			//是否显示删除按钮
                showRenameBtn: true,			//是否显示修改按钮
            },
            button: {
            	isTopButton: "添加根节点"
            }
	    };
    
	var log, className = "dark";
    function beforeRemove(treeId, treeNode) {											//删除节点事件
		className = (className === "dark" ? "":"dark");
		zTree.selectNode(treeNode);
		if(confirm("确认删除 节点 -- " + treeNode.name + " 吗？，该操作会删除该节点下所有子节点")) {
			var arrays = new Array();							//存放所有节点的id
        	return removeNode(treeNode.id);						//调用删除节点方法
		};
		return false;
	}
    
    function zTreeBeforeDrop(treeId, treeNodes, targetNode, moveType, isCopyBoolean) {	//移动节点
		if(moveType == null) {
			
    	} else if(isCopyBoolean == false) {					//移动
    		var targetNodeId;
    		if(targetNode == null) {
    			targetNodeId = "0";
    		} else {
    			targetNodeId = targetNode.id;
    		}
       		var flag = moveNode(treeNodes[0].id, targetNodeId);
       		return flag;
    	}
    }
    
    function beforeRename(treeId, treeNode, newName, isCancel) {						//编辑节点事件
    	if (newName.length == 0) {					//是否为null字符串
			alert("节点名不能为空");
			zTree.cancelEditName();
            return false;
		}
    	else if(newName == treeNode.name) {			//是否没有改变
    	} else {									//执行修改
    		var flag = editorNode(treeNode.id, newName);			//调用编辑节点ajax方法
    		if(flag == false) {										//后台操作失败时设置为原来的值
    			zTree.cancelEditName();
    			return false;
    		}
    	}
		return true; 
	}
    
    var newCount = 1;
	function addHoverDom(treeId, treeNode) {											//鼠标移入事件
		/*var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			if(confirm("确认为 " + treeNode.name + " 添加子节点吗？") == true) {
				var c = zTree.addNodes(treeNode, {id:++maxNodes, pId:treeNode.id, name:""});
				var node = zTree.getNodeByTId(c[0].tId);		//添加节点返回的是数组类型
				zTree.editName(node);							//设置节点进入编辑状态
				isAdd = true;									//设置后面编辑节点后传入后台为添加节点
				return false;
			}
		});*/
	};
    function removeHoverDom(treeId, treeNode) {											//鼠标移出事件	
		$("#addBtn_"+treeNode.tId).unbind().remove();
	};
	function addNode(id, name) {		//添加节点方法，防止用户不使用addNode()方法报错问题，一般用户通过重写此方法来与数据库同步，一下方法一致				
		return true;
	}
	function editorNode(id, name) {											
		return true;
	}
	function removeNode(arrays) {												
		return true;
	}
	function moveNode(arrays) {												
		return true;
	} 
	function checkedNodes(arrays) {												
		return true;
	}
	
	function btnCheckedNodes() {									//批量操作
	    var nodes=zTree.getCheckedNodes(true);
		var array = new Array();									//获取批量的节点id
	    for(var i=0;i<nodes.length;i++){
	    	array[i] = nodes[i].id;
    	}
	    checkedNodes(array);
	}
	function zTreeOnClick(event, treeId, treeNode) {
		$("#no1").attr("src", "skipEdit?id=" +treeNode.id);
		/*var allNodes = zTree.getNodes();							//拿到根节点
		var all = zTree.transformToArray(allNodes);					//拿到所有节点
		editNodeDialog(all,treeNode);*/
	}
	
	function editNodeDialog() {
		
	}
