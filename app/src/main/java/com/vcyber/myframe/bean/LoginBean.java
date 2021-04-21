package com.vcyber.myframe.bean;


import com.vcyber.myframe.base.BaseClassResultBean;

import java.util.ArrayList;

/**
 * Create by zjl on 2019/5/6
 * ---- ----
 */
public class LoginBean extends BaseClassResultBean<LoginBean.Result> {
    public static class Result {
        private String id;          //id
        private String loginname;
        private String password;
        private String code;//userCode
        private String name;
        private String phone;
        private String usertype;//用户类型 1、经销商 2、车厂
        private String dealer;//1经销商代码 2车厂代码
        private String officeid;//1经销商网点 2部门id
        private String address;
        private String iconurl;//用户头像
        private String ischecked;// 0-需要确认网点信息 经销商人员不能有业务操作
        private String allowaupdateaddress;//是否可以确认网点信息 0不允许修改
        private Item item;
        private Itemwd itemwd;
        private ArrayList<Itemmenu> itemmenu;
        private String assesstoken;


        public String getAssesstoken() {
            return assesstoken;
        }

        public void setAssesstoken(String assesstoken) {
            this.assesstoken = assesstoken;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLoginname() {
            return loginname;
        }

        public void setLoginname(String loginname) {
            this.loginname = loginname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUsertype() {
            return usertype;
        }

        public void setUsertype(String usertype) {
            this.usertype = usertype;
        }

        public String getDealer() {
            return dealer;
        }

        public void setDealer(String dealer) {
            this.dealer = dealer;
        }

        public String getOfficeid() {
            return officeid;
        }

        public void setOfficeid(String officeid) {
            this.officeid = officeid;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIconurl() {
            return iconurl;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public String getIschecked() {
            return ischecked;
        }

        public void setIschecked(String ischecked) {
            this.ischecked = ischecked;
        }

        public String getAllowaupdateaddress() {
            return allowaupdateaddress;
        }

        public void setAllowaupdateaddress(String allowaupdateaddress) {
            this.allowaupdateaddress = allowaupdateaddress;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public Itemwd getItemwd() {
            return itemwd;
        }

        public void setItemwd(Itemwd itemwd) {
            this.itemwd = itemwd;
        }

        public ArrayList<Itemmenu> getItemmenu() {
            return itemmenu;
        }

        public void setItemmenu(ArrayList<Itemmenu> itemmenu) {
            this.itemmenu = itemmenu;
        }

        public static class Item {

            /**
             * id : 22608529
             * vdqcode : 大区代码
             * vdqname : 大区名称
             * vsdccode : 商代处代码
             * vsdcname : 商代处名称
             * vprovincecode : 省份代码
             * vprovincename : 省份名称
             * vcitycode : 城市代码
             * vcityname : 城市名称
             * vcountycode : 区县代码
             * vcountyname : 区县名称
             * vdealer : 经销商代码
             * vdealertext : 经销商简称
             * vdealerlongtext : 经销商全称
             * vdealertype : 经销商类型  4S、销售、服务
             * <p>
             * <p>
             * <p>
             * vfactorybrandcode : 车厂code
             * vfactorybrandname : 车厂名称
             */

            private String id;
            private String vdqcode;
            private String vdqname;
            private String vsdccode;
            private String vsdcname;
            private String vprovincecode;
            private String vprovincename;
            private String vcitycode;
            private String vcityname;
            private String vcountycode;
            private String vcountyname;
            private String vdealer;
            private String vdealertext;
            private String vdealerlongtext;
            private String vdealertype;


            private String vfactorybrandcode;
            private String vfactorybrandname;


            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getVdqcode() {
                return vdqcode;
            }

            public void setVdqcode(String vdqcode) {
                this.vdqcode = vdqcode;
            }

            public String getVdqname() {
                return vdqname;
            }

            public void setVdqname(String vdqname) {
                this.vdqname = vdqname;
            }

            public String getVsdccode() {
                return vsdccode;
            }

            public void setVsdccode(String vsdccode) {
                this.vsdccode = vsdccode;
            }

            public String getVsdcname() {
                return vsdcname;
            }

            public void setVsdcname(String vsdcname) {
                this.vsdcname = vsdcname;
            }

            public String getVprovincecode() {
                return vprovincecode;
            }

            public void setVprovincecode(String vprovincecode) {
                this.vprovincecode = vprovincecode;
            }

            public String getVprovincename() {
                return vprovincename;
            }

            public void setVprovincename(String vprovincename) {
                this.vprovincename = vprovincename;
            }

            public String getVcitycode() {
                return vcitycode;
            }

            public void setVcitycode(String vcitycode) {
                this.vcitycode = vcitycode;
            }

            public String getVcityname() {
                return vcityname;
            }

            public void setVcityname(String vcityname) {
                this.vcityname = vcityname;
            }

            public String getVcountycode() {
                return vcountycode;
            }

            public void setVcountycode(String vcountycode) {
                this.vcountycode = vcountycode;
            }

            public String getVcountyname() {
                return vcountyname;
            }

            public void setVcountyname(String vcountyname) {
                this.vcountyname = vcountyname;
            }

            public String getVdealer() {
                return vdealer;
            }

            public void setVdealer(String vdealer) {
                this.vdealer = vdealer;
            }

            public String getVdealertext() {
                return vdealertext;
            }

            public void setVdealertext(String vdealertext) {
                this.vdealertext = vdealertext;
            }

            public String getVdealerlongtext() {
                return vdealerlongtext;
            }

            public void setVdealerlongtext(String vdealerlongtext) {
                this.vdealerlongtext = vdealerlongtext;
            }

            public String getVdealertype() {
                return vdealertype;
            }

            public void setVdealertype(String vdealertype) {
                this.vdealertype = vdealertype;
            }

            public String getVfactorybrandcode() {
                return vfactorybrandcode;
            }

            public void setVfactorybrandcode(String vfactorybrandcode) {
                this.vfactorybrandcode = vfactorybrandcode;
            }

            public String getVfactorybrandname() {
                return vfactorybrandname;
            }

            public void setVfactorybrandname(String vfactorybrandname) {
                this.vfactorybrandname = vfactorybrandname;
            }
        }

        public static class Itemwd{

            /**
             * id : 83073
             * vdealer : 02-32
             * vbranch : 322
             * vbranchtext : 汉沽店
             * vbranchlongtext : 天津点特汽车贸易有限公司
             * vbranchlevel : 二级营业网点
             * breceiveaddr : null
             * vlinkman : 闫长河
             * vmobil : 13802030613
             */

            private int id;
            private String vdealer;
            private String vbranch;
            private String vbranchtext;
            private String vbranchlongtext;
            private String vbranchlevel;
            private String breceiveaddr;
            private String vlinkman;
            private String vmobil;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getVdealer() {
                return vdealer;
            }

            public void setVdealer(String vdealer) {
                this.vdealer = vdealer;
            }

            public String getVbranch() {
                return vbranch;
            }

            public void setVbranch(String vbranch) {
                this.vbranch = vbranch;
            }

            public String getVbranchtext() {
                return vbranchtext;
            }

            public void setVbranchtext(String vbranchtext) {
                this.vbranchtext = vbranchtext;
            }

            public String getVbranchlongtext() {
                return vbranchlongtext;
            }

            public void setVbranchlongtext(String vbranchlongtext) {
                this.vbranchlongtext = vbranchlongtext;
            }

            public String getVbranchlevel() {
                return vbranchlevel;
            }

            public void setVbranchlevel(String vbranchlevel) {
                this.vbranchlevel = vbranchlevel;
            }

            public String getBreceiveaddr() {
                return breceiveaddr;
            }

            public void setBreceiveaddr(String breceiveaddr) {
                this.breceiveaddr = breceiveaddr;
            }

            public String getVlinkman() {
                return vlinkman;
            }

            public void setVlinkman(String vlinkman) {
                this.vlinkman = vlinkman;
            }

            public String getVmobil() {
                return vmobil;
            }

            public void setVmobil(String vmobil) {
                this.vmobil = vmobil;
            }
        }

        public static class Itemmenu{

            /**
             * menuid : 2888bb13e5ba4a8d9884a2dd2727582b
             * parentid : 5eeb9b0a64dd4008a69041a2083a7428
             * menuname : 经销商
             */

            private String menuid;
            private String parentid;
            private String menuname;

            public String getMenuid() {
                return menuid;
            }

            public void setMenuid(String menuid) {
                this.menuid = menuid;
            }

            public String getParentid() {
                return parentid;
            }

            public void setParentid(String parentid) {
                this.parentid = parentid;
            }

            public String getMenuname() {
                return menuname;
            }

            public void setMenuname(String menuname) {
                this.menuname = menuname;
            }
        }

    }
}
