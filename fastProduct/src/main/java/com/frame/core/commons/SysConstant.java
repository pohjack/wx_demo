/**    
* @Title: SysConstant.java
* @Package com.frame.core.commons
* @Description: TODO(用一句话描述该文件做什么)
* @author: lzl
* @date 2016年7月4日 上午9:55:39
* @version V1.0
*/
package com.frame.core.commons;

public interface SysConstant {

    /***页面跳转常量 Page****/
    /**
    * @ PAGE_JUMP_TOLIST : 跳转到列表页面
    */
    String PAGE_JUMP_TOLIST = "List";

    /**
    * @ PAGE_JUMP_ADD : 跳转到添加页面
    */
    String PAGE_JUMP_ADD = "Add";

    /**
    * @ PAGE_JUMP_EDIT : 跳转到编辑页面
    */
    String PAGE_JUMP_EDIT = "Edit";

    /**
    * @ PAGE_JUMP_VIEW : 跳转到查看页面
    */
    String PAGE_JUMP_VIEW = "View";

    /**
    * @ PAGE_PATH_SEPARATOR : "/"
    */
    String PAGE_PATH_SEPARATOR = "/";
    /**
     * @ TREE_OBJECT : 树对象返回页面的名称
     */
    String TREE_OBJECT = "treeNodes";
    /**
     * @ PASS_METHOD : 页面命名规范放行的方法在属性文件中的名称
     */
    String PASS_METHOD = "passMethod";
    /**
     * @ RESOURCE_EDIT : 资源查看的权限标识符
     */
    String RESOURCE_VIEW = "resource:view";
    /**
     * @ RESOURCE_EDIT : 资源编辑的权限标识符
     */
    String RESOURCE_EDIT = "resource:edit";
    /**
     * @ RESOURCE_INSERT : 资源禁用权限标识符
     */
    String RESOURCE_CHANGE_STATE = "resource:change_state";
    /**
     * @ RESOURCE_INSERT : 资源新增的权限标识符
     */
    String RESOURCE_INSERT = "resource:insert";
    /**
     * @ RESOURCE_INSERT : 资源删除的权限标识符
     */
    String RESOURCE_REMOVE = "resource:remove";
    /**
     * @ ORGANIZATION_EDIT : 组织机构编辑的权限标识符
     */
    String ORGANIZATION_EDIT = "organization:edit";
    /**
     * @ ORGANIZATION_INSERT : 组织机构编辑的权限标识符
     */
    String ORGANIZATION_INSERT = "organization:insert";
    /**
     * @ ORGANIZATION_VIEW : 组织机构编辑的权限标识符
     */
    String ORGANIZATION_VIEW = "organization:view";
    /**
     * @ ORGANIZATION_VIEW : 组织机构删除的权限标识符
     */
    String ORGANIZATION_REMOVE = "organization:remove";
    /**
     * @ RESOURCE_INSERT : 组织机构禁用权限标识符
     */
    String ORGANIZATION_CHANGE_STATE = "organization:change_state";
    /**
     * @ USER_EDIT :用户编辑的权限标识符
     */
    String USER_EDIT = "user:edit";
    /**
     * @ USER_UNLOCK :用户解锁的权限标识符
     */
    String USER_UNLOCK = "user:unlock";
    /**
     * @ USER_REMOVE :用户删除的权限标识符
     */
    String USER_REMOVE = "user:remove";
    /**
     * @ USER_CHANGE_STATE :用户禁用与启用权限标识符
     */
    String USER_CHANGE_STATE = "user:change_state";
    /**
     * @ USER_CHANGE_PASSWORD :用户密码修改权限标识符
     */
    String USER_CHANGE_PASSWORD = "user:change_password";

    /**
     * @ USER_CHANGE_PASSWORD :角色查询标识符
     */
    String ROLE_VIEW = "role:view";
    /**
     * @ USER_CHANGE_PASSWORD :角色编辑标识符
     */
    String ROLE_EIDT = "role:edit";
    /**
     * @ USER_CHANGE_PASSWORD :角色删除标识符
     */
    String ROLE_REMOVE = "role:remove";
    /**
     * @ USER_CHANGE_PASSWORD :角色禁用标识符
     */
    String ROLE_CHANGE_STATE = "role:change_state";

    /**
     * @ USER_CHANGE_PASSWORD :案件编辑标示符
     */
    String CASE_ADD = "case:add";
    /**
     * @ USER_CHANGE_PASSWORD :案件编辑标示符
     */
    String CASE_EDIT = "case:edit";
    /**
     * @ USER_CHANGE_PASSWORD :案件删除标示符
     */
    String CASE_REMOVE = "case:remove";

    /**
     * @ USER_CHANGE_PASSWORD :案件列表标示符
     */
    String CASE_TOLIST = "case:to_list";
    /**
     * @ USER_CHANGE_PASSWORD :统计数据标示符
     */
    String STATS_VIEW = "stats:view";
    /**
     * @ USER_CHANGE_PASSWORD :数据字典管理标示符
     */
    String DICT_VIEW = "dict:view";
    /**
     * @ USER_CHANGE_PASSWORD :文书模板管理标示符
     */
    String TEMP_VIEW = "temp:view";
    /**
     * @ USER_CHANGE_PASSWORD :许可证管理标示符
     */
    String LICENCE_VIEW = "licence:view";
    /**
     * @ USER_CHANGE_PASSWORD :卷烟管理标示符
     */
    String CIGAR_VIEW = "cigar:view";
    /**
     * @ USER_CHANGE_PASSWORD :法律管理标示符
     */
    String LAW_VIEW = "law:view";
    /**
     * @ USER_CHANGE_PASSWORD :区域标示符
     */
    String AREA_VIEW = "area:view";
    /**
     * @ USER_CHANGE_PASSWORD :基础模板标示符
     */
    String BASETEMP_VIEW = "basetemp:view";
    /**
     * @ USER_CHANGE_PASSWORD :进出存帐标示符
     */
    String CASE_ACCOUNT = "case:account";
    /**
     * @ USER_CHANGE_PASSWORD :Excel导入标示符
     */
    String EXCEL_IMPOT = "excel:import";
    /**
     * @ USER_CHANGE_PASSWORD :Excel导出标示符
     */
    String EXCEL_EXPORT = "excel:export";
    /**
     * @ USER_CHANGE_PASSWORD :卷烟编辑标示符
     */
    String CIGAR_EDIT = "cigar:edit";

    int TABLE_DATA_START = 0;

    int TABLE_DATA_LENGTH = 2;

    /**
     * @ INITPASSWORD :添加用户时候未填写默认为123456
     */
    String INITPASSWORD = "123456";

}
