package com.crm.dao.zyp;

import com.crm.common.ContextUtils;
import com.crm.db.DBPool;
import com.crm.vo.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class BaseDAO {

    //判断是否能删除
    public boolean isDel(String tableName, String tableId, String tableName1, String id) {
        boolean b = false;
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from " + tableName + " where " + tableId + "='" + id + "'";
            sql += " and " + tableId + " in (select " + tableId + " from " + tableName1 + ")";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                b = true;
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
    public boolean isDels(String tableName, String tableId,String id) {
        boolean b = false;
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from " + tableName + " where " + tableId + "= '"+id+"' ";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                b = true;
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    //删除字段方法
    public void del(String tableName, String tableid, String id) {
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete from " + tableName + " where " + tableid + " = '" + id + "'";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询
    public int findCount(String tableName) {
        int count = 0;
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from " + tableName;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return count;
    }

    //查询客户联系
    public int findCountContact(String custId) {
        int count = 0;
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from Contact where custid=" + custId;
            System.out.println(sql);

            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return count;
    }


    //-----部门管理
    //新建部门
    public void addDep(DepVo d) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "insert into Dep values('";
            sql += d.getDepname() + "','";
            sql += d.getChairman() + "','";
            sql += d.getDescription() + "')";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据部门id查找
    public DepVo findDepId(int depId) {
        DepVo d = new DepVo();
        Connection conn = DBPool.openDB();
        try {
            String sql = "select * from Dep where depid=" + depId;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                d.setDepid(rs.getInt("depId"));
                d.setDepname(rs.getString("depname"));
                d.setChairman(rs.getString("chairman"));
                d.setDescription(rs.getString("description"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    //修改部门
    public void updateDep(DepVo d) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update Dep set depname='";
            sql += d.getDepname() + "',chairman='";
            sql += d.getChairman() + "',description='";
            sql += d.getDescription() + "' where depid=" + d.getDepid();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除部门
    public void depDep(int depid) {
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete from Dep where depid=" + depid;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询部门记录数
    public int findCountDep() {
        int count = 0;
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from Dep";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return count;
    }

    public List<DepVo> findPageDep(int startRow, int pageSize) {
        List<DepVo> list = new ArrayList<DepVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " * from Dep  where depid not in (";
            sql += "select top " + startRow + " depid from Dep)";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                DepVo dep = new DepVo();
                dep.setDepid(rs.getInt("depid"));
                dep.setDepname(rs.getString("depname"));
                dep.setChairman(rs.getString("chairman"));
                dep.setDescription(rs.getString("description"));
                list.add(dep);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //-----岗位表
    //岗位表
    public void addDegrees(DegreesVo d) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "insert into Degrees values('";
            sql += d.getDegreename() + "')";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据岗位id查找
    public DegreesVo findDegreesId(int degreeid) {
        DegreesVo d = new DegreesVo();
        Connection conn = DBPool.openDB();
        try {
            String sql = "select * from Degrees where degreeid=" + degreeid;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                d.setDegreeid(rs.getInt("degreeid"));
                d.setDegreename(rs.getString("degreename").trim());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    //修改岗位
    public void updateDegrees(DegreesVo d) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update Degrees set degreename='";
            sql += d.getDegreename() + "' where degreeid=" + d.getDegreeid();
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除岗位
    public void depDegrees(int degreeid) {
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete from Degrees where degreeid=" + degreeid;
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询岗位记录数
    public int findCountDegrees() {
        int count = 0;
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from Degrees";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return count;
    }

    //岗位分页
    public List<DegreesVo> findPageDegrees(int startRow, int pageSize) {
        List<DegreesVo> list = new ArrayList<DegreesVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " * from Degrees  where degreeid not in (";
            sql += "select top " + startRow + " degreeid from Degrees)";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                DegreesVo d = new DegreesVo();
                d.setDegreeid(rs.getInt("degreeid"));
                d.setDegreename(rs.getString("degreename").trim());
                list.add(d);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //-----单位表
    //新增单位表
    public void addUnit(UnitVo u) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "insert into Unit values('";
            sql += u.getUnitName() + "')";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据单位id查找
    public UnitVo findUnitId(int unitId) {
        UnitVo u = new UnitVo();
        Connection conn = DBPool.openDB();
        try {
            String sql = "select * from Unit where unitId=" + unitId;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                u.setUnitId(rs.getInt("unitId"));
                u.setUnitName(rs.getString("unitName").trim());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    //修改单位
    public void updateUnit(UnitVo u) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update Unit set unitName='";
            sql += u.getUnitName() + "' where unitId=" + u.getUnitId();
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除单位
    public void depUnit(int unitId) {
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete from Unit where unitId=" + unitId;
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询岗位记录数
    public int findCountUnit() {
        int count = 0;
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from Unit";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return count;
    }

    //岗位分页
    public List<UnitVo> findPageUnit(int startRow, int pageSize) {
        List<UnitVo> list = new ArrayList<UnitVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " * from Unit  where unitId not in (";
            sql += "select top " + startRow + " unitId from Unit)";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                UnitVo u = new UnitVo();
                u.setUnitId(rs.getInt("unitId"));
                u.setUnitName(rs.getString("unitName").trim());
                list.add(u);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //-----用户表
    //查找部门表
    //查找部门
    public List<DepVo> findDep() {
        List<DepVo> list = new ArrayList<DepVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from Dep";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                DepVo dep = new DepVo();
                dep.setDepid(rs.getInt("depid"));
                dep.setDepname(rs.getString("depname"));
                dep.setChairman(rs.getString("chairman"));
                dep.setDescription(rs.getString("description"));
                list.add(dep);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //查找岗位
    public List<DegreesVo> findDegrees() {
        List<DegreesVo> list = new ArrayList<DegreesVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from Degrees";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                DegreesVo dep = new DegreesVo();
                dep.setDegreeid(rs.getInt("degreeid"));
                dep.setDegreename(rs.getString("degreename"));
                list.add(dep);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //新增用户
    public void addUsers(UsersVo u) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "insert into Users values('";
            sql += u.getUsername() + "','";
            sql += u.getPassword() + "',";
            sql += u.getDepid() + ",";
            sql += u.getDegreeid() + ",'";
            sql += u.getJobname() + "',";
            sql += u.getManagerType() + ",'";
            sql += u.getMobile() + "','";
            sql += u.getEmail() + "','";
            sql += u.getQqcode() + "','";
            sql += u.getWeixin() + "','";
            sql += u.getCardno() + "','";
            sql += u.getBankName() + "','";
            sql += u.getBankCardNo() + "',";
            if (u.getJoinDate().equals("")) {
                sql += "null,";
            } else {
                sql += "'" + u.getJoinDate() + "',";
            }
            if (u.getWorkDate().equals("")) {
                sql += "null,";
            } else {
                sql += "'" + u.getWorkDate() + "',";
            }
            if (u.getLevelDate().equals("")) {
                sql += "null,";
            } else {
                sql += "'" + u.getLevelDate() + "',";
            }
            sql += u.getBaseSalary() + ",";
            sql += u.getDegreeSalary() + ",'";
            sql += u.getAddr() + "',";
            sql += u.getStatus() + ",'";
            sql += u.getRemark() + "' )";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据用户id查找
    public UsersVo findUsersId(int userid) {
        UsersVo u = new UsersVo();
        Connection conn = DBPool.openDB();
        try {
            String sql = "select * from Users where userid=" + userid;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                u.setUserid(rs.getInt("userid"));
                u.setUsername(rs.getString("username").trim());
                u.setPassword(rs.getString("password").trim());
                u.setDepid(rs.getInt("depid"));
                u.setDegreeid(rs.getInt("degreeid"));
                u.setJobname(rs.getString("jobname").trim());
                u.setManagerType(rs.getInt("managerType"));
                u.setMobile(rs.getString("mobile").trim());
                u.setEmail(rs.getString("email").trim());
                u.setQqcode(rs.getString("qqcode").trim());
                u.setWeixin(rs.getString("weixin").trim());
                u.setCardno(rs.getString("cardno").trim());
                u.setBankName(rs.getString("bankName").trim());
                u.setBankCardNo(rs.getString("bankCardNo").trim());
                u.setJoinDate(ContextUtils.dateToStrShort(rs.getTimestamp("joinDate")));
                Date workDate = rs.getTimestamp("workDate");
                Date levelDate = rs.getTimestamp("levelDate");
                if (workDate != null) {
                    u.setWorkDate(ContextUtils.dateToStrShort(workDate));
                } else {
                    u.setWorkDate("");
                }
                if (levelDate != null) {
                    u.setLevelDate(ContextUtils.dateToStrShort(levelDate));
                } else {
                    u.setLevelDate("");
                }
                u.setBaseSalary(rs.getFloat("baseSalary"));
                u.setDegreeSalary(rs.getFloat("degreeSalary"));
                u.setAddr(rs.getString("addr").trim());
                u.setStatus(rs.getInt("status"));
                u.setRemark(rs.getString("remark").trim());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    //修改用户
    public void updateUsers(UsersVo u) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update Users set depid=";
            sql += u.getDepid() + ",degreeid=";
            sql += u.getDegreeid() + ",jobname='";
            sql += u.getJobname() + "',managerType=";
            sql += u.getManagerType() + ",email='";
            sql += u.getEmail() + "',qqcode='";
            sql += u.getQqcode() + "',weixin='";
            sql += u.getWeixin() + "',cardno='";
            sql += u.getCardno() + "',bankName='";
            sql += u.getBankName() + "',bankCardNo='";
            sql += u.getBankCardNo() + "',joinDate=";
            if (u.getJoinDate().equals("")) {
                sql += "null,workDate=";
            } else {
                sql += "'" + u.getJoinDate() + "',workDate=";
            }
            if (u.getWorkDate().equals("")) {
                sql += "null,levelDate=";
            } else {
                sql += "'" + u.getWorkDate() + "',levelDate=";
            }
            if (u.getLevelDate().equals("")) {
                sql += "null,baseSalary=";
            } else {
                sql += "'" + u.getLevelDate() + "',baseSalary=";
            }
            sql += u.getBaseSalary() + ",degreeSalary=";
            sql += u.getDegreeSalary() + ",addr='";
            sql += u.getAddr() + "',remark='";
            sql += u.getRemark() + "' where userid=" + u.getUserid();
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除用户
    public void depUsers(int userid) {
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete from Users where userid=" + userid;
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询用户记录数
    public int findCountUsers() {
        int count = 0;
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from Users";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return count;
    }

    //用户分页
    public List<UsersVo> findPageUsers(int startRow, int pageSize) {
        List<UsersVo> list = new ArrayList<UsersVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " u.*,d.depname,de.degreename from Users u inner join ";
            sql += "Dep d on u.depid=d.depid ";
            sql += "inner join Degrees de on de.degreeid=u.degreeid ";
            sql += "where userid not in (";
            sql += "select top " + startRow + " userid from Users)";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                UsersVo u = new UsersVo();
                u.setUserid(rs.getInt("userid"));
                u.setUsername(rs.getString("username").trim());
                u.setPassword(rs.getString("password").trim());
                u.setDepid(rs.getInt("depid"));
                u.setDegreeid(rs.getInt("degreeid"));
                u.setJobname(rs.getString("jobname").trim());
                u.setManagerType(rs.getInt("managerType"));
                u.setMobile(rs.getString("mobile").trim());
                u.setEmail(rs.getString("email").trim());
                u.setQqcode(rs.getString("qqcode").trim());
                u.setWeixin(rs.getString("weixin").trim());
                u.setCardno(rs.getString("cardno").trim());
                u.setBankName(rs.getString("bankName").trim());
                u.setBankCardNo(rs.getString("bankCardNo").trim());
                u.setJoinDate(ContextUtils.dateToStrShort(rs.getTimestamp("joinDate")));
                Date workDate = rs.getTimestamp("workDate");
                Date levelDate = rs.getTimestamp("levelDate");
                if (workDate != null) {
                    u.setWorkDate(ContextUtils.dateToStrShort(workDate));
                } else {
                    u.setWorkDate(workDate + "");
                }
                if (levelDate != null) {
                    u.setLevelDate(ContextUtils.dateToStrShort(levelDate));
                } else {
                    u.setLevelDate(levelDate + "");
                }

                u.setBaseSalary(rs.getFloat("baseSalary"));
                u.setDegreeSalary(rs.getFloat("degreeSalary"));
                u.setAddr(rs.getString("addr").trim());
                u.setStatus(rs.getInt("status"));
                u.setDepname(rs.getString("depname").trim());
                u.setDegreename(rs.getString("degreename").trim());
                u.setRemark(rs.getString("remark").trim());
                list.add(u);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //判断账号名称是否存在
    public boolean isUser(String username) {
        boolean b = false;
        Connection conn = DBPool.openDB();
        try {
            String sql = "select * from Users where username='" + username + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                b = true;
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;

    }

    public boolean updateStatus(int userid) {
        Connection conn = DBPool.openDB();
        boolean b = false;
        try {
            String sql = "select status from Users where userid=" + userid;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                if (rs.getInt("status") == 1) {
                    b = true;
                } else {
                    b = false;
                }
            }
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    //禁用/启用
    public void updateStatus(int userid, int status) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update Users set status=" + status + " where userid=" + userid;
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //修改密码
    public void updatePassword(int userid, String password) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update Users set password='" + password + "' where userid=" + userid;
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //用户登录
    public UsersVo userLogin(String username) {
        Connection conn = DBPool.openDB();
        UsersVo u = new UsersVo();
        try {
            String sql = "select * from Users where username='" + username + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                u.setUserid(rs.getInt("userid"));
                u.setUsername(rs.getString("username").trim());
                u.setPassword(rs.getString("password").trim());
                u.setDepid(rs.getInt("depid"));
                u.setDegreeid(rs.getInt("degreeid"));
                u.setJobname(rs.getString("jobname").trim());
                u.setManagerType(rs.getInt("managerType"));
                u.setMobile(rs.getString("mobile").trim());
                u.setEmail(rs.getString("email").trim());
                u.setQqcode(rs.getString("qqcode").trim());
                u.setWeixin(rs.getString("weixin").trim());
                u.setCardno(rs.getString("cardno").trim());
                u.setBankName(rs.getString("bankName").trim());
                u.setBankCardNo(rs.getString("bankCardNo").trim());
                u.setJoinDate(ContextUtils.dateToStrShort(rs.getTimestamp("joinDate")));
                Date workDate = rs.getTimestamp("workDate");
                Date levelDate = rs.getTimestamp("levelDate");
                if (workDate != null) {
                    u.setWorkDate(ContextUtils.dateToStrShort(workDate));
                } else {
                    u.setWorkDate(workDate + "");
                }
                if (levelDate != null) {
                    u.setLevelDate(ContextUtils.dateToStrShort(levelDate));
                } else {
                    u.setLevelDate(levelDate + "");
                }
                u.setBaseSalary(rs.getFloat("baseSalary"));
                u.setDegreeSalary(rs.getFloat("degreeSalary"));
                u.setAddr(rs.getString("addr").trim());
                u.setStatus(rs.getInt("status"));
                u.setRemark(rs.getString("remark").trim());
            } else {
                u = null;
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    //-----供应商管理
    //新建供应商
    public void addSupplier(SupplierVo s) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "insert into Supplier values('";
            sql += s.getSupplierName() + "','";
            sql += s.getBankAccount() + "','";
            sql += s.getBankName() + "','";
            sql += s.getContact() + "','";
            sql += s.getPhone() + "','";
            sql += s.getAddr() + "','";
            sql += s.getRemark() + "' )";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据供应商id查找
    public SupplierVo findSupplierId(int supplierId) {
        SupplierVo s = new SupplierVo();
        Connection conn = DBPool.openDB();
        try {
            String sql = "select * from Supplier where supplierId=" + supplierId;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                s.setSupplierId(rs.getInt("supplierId"));
                s.setSupplierName(rs.getString("supplierName").trim());
                s.setBankAccount(rs.getString("bankAccount").trim());
                s.setBankName(rs.getString("bankName").trim());
                s.setContact(rs.getString("contact").trim());
                s.setPhone(rs.getString("phone").trim());
                s.setAddr(rs.getString("addr").trim());
                s.setRemark(rs.getString("remark").trim());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    //修改供应商
    public void updateSupplier(SupplierVo s) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update Supplier set supplierName='";
            sql += s.getSupplierName() + "',bankAccount='";
            sql += s.getBankAccount() + "',bankName='";
            sql += s.getBankName() + "',contact='";
            sql += s.getContact() + "',phone='";
            sql += s.getPhone() + "',addr='";
            sql += s.getAddr() + "',remark='";
            sql += s.getRemark() + "' where supplierId=" + s.getSupplierId();
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除供应商
    public void depSupplier(int supplierId) {
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete from Supplier where supplierId=" + supplierId;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询部门记录数
    public int findCountSupplier() {
        int count = 0;
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from Supplier";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return count;
    }

    //查找供应商分页
    public List<SupplierVo> findPageSupplier(int startRow, int pageSize) {
        List<SupplierVo> list = new ArrayList<SupplierVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " * from Supplier  where supplierId not in (";
            sql += "select top " + startRow + " supplierId from Supplier)";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                SupplierVo s = new SupplierVo();
                s.setSupplierId(rs.getInt("supplierId"));
                s.setSupplierName(rs.getString("supplierName").trim());
                s.setBankAccount(rs.getString("bankAccount").trim());
                s.setBankName(rs.getString("bankName").trim());
                s.setContact(rs.getString("contact").trim());
                s.setPhone(rs.getString("phone").trim());
                s.setAddr(rs.getString("addr").trim());
                s.setRemark(rs.getString("remark").trim());
                list.add(s);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    //-----商品管理
    //初始化
    public List<UnitVo> findUnit() {
        List<UnitVo> list = new ArrayList<UnitVo>();
        Connection conn = DBPool.openDB();
        try {
            String sql = "select * from Unit ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                UnitVo u = new UnitVo();
                u.setUnitId(rs.getInt("unitId"));
                u.setUnitName(rs.getString("unitName").trim());
                list.add(u);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SupplierVo> findSupplier() {
        List<SupplierVo> list = new ArrayList<SupplierVo>();
        Connection conn = DBPool.openDB();
        try {
            String sql = "select * from Supplier ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                SupplierVo s = new SupplierVo();
                s.setSupplierId(rs.getInt("supplierId"));
                s.setSupplierName(rs.getString("supplierName").trim());
                list.add(s);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //新建商品
    public void addProduct(ProductVo p) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "insert into Product values('";
            sql += p.getProdid() + "','";
            sql += p.getProdname() + "','";
            sql += p.getProdModel() + "',";
            sql += p.getProdUnit() + ",'";
            sql += p.getProdStyle() + "',";
            sql += p.getProdCount() + ",";
            sql += p.getInPrice() + ",";
            sql += p.getSalePrice() + ",";
            sql += p.getLowSalePrice() + ",'";
            sql += p.getProdSerial() + "','";
            sql += p.getCdKey() + "','";
            sql += p.getSaleStatus() + "',";
            sql += p.getSupplierId() + ",'";
            sql += p.getRemark() + "' )";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据商品id查找
    public ProductVo findProductId(String prodid) {
        ProductVo p = new ProductVo();
        Connection conn = DBPool.openDB();
        try {
            String sql = "select  p.*,u.unitName from Product p ";
            sql += "inner join Unit u on p.prodUnit=u.unitId where prodid='" + prodid + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                p.setProdid(rs.getString("prodid").trim());
                p.setProdname(rs.getString("prodname").trim());
                p.setProdModel(rs.getString("prodModel").trim());
                p.setProdUnit(rs.getInt("prodUnit"));
                p.setProdStyle(rs.getString("prodStyle").trim());
                p.setProdCount(rs.getInt("prodCount"));
                p.setInPrice(rs.getFloat("inPrice"));
                p.setSalePrice(rs.getFloat("salePrice"));
                p.setLowSalePrice(rs.getFloat("lowSalePrice"));
                p.setProdSerial(rs.getString("prodSerial").trim());
                p.setCdKey(rs.getString("cdKey").trim());
                p.setSaleStatus(rs.getString("saleStatus").trim());
                p.setSupplierId(rs.getInt("supplierId"));
                p.setRemark(rs.getString("remark"));
                p.setProdUnitName(rs.getString("unitName"));
                System.out.println(p.getRemark());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    //修改商品
    public void updateProduct(ProductVo p) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update Product set prodname='";
            sql += p.getProdname() + "',prodModel='";
            sql += p.getProdModel() + "',prodUnit=";
            sql += p.getProdUnit() + ",prodStyle='";
            sql += p.getProdStyle() + "',prodCount=";
            sql += p.getProdCount() + ",inPrice=";
            sql += p.getInPrice() + ",salePrice=";
            sql += p.getSalePrice() + ",lowSalePrice=";
            sql += p.getLowSalePrice() + ",prodSerial='";
            sql += p.getProdSerial() + "',cdKey='";
            sql += p.getCdKey() + "',supplierId=";
            sql += p.getSupplierId() + ",remark='";
            sql += p.getRemark() + "' where prodid='" + p.getProdid() + "'";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询商品数量
    public int findProductCount(String prodid) {
        Connection conn = DBPool.openDB();
        int count = 0;
        try {
            String sql = "select prodCount from Product where prodid='" + prodid + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("prodCount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    //删除商品
    public void depProduct(int prodid) {
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete from Product where prodid=" + prodid;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询商品记录数
    public int findCountProduct() {
        int count = 0;
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from Product";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return count;
    }

    //查找供应商分页
    public List<ProductVo> findPageProduct(int startRow, int pageSize) {
        List<ProductVo> list = new ArrayList<ProductVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " p.*,u.unitName,s.supplierName  from Product p ";
            sql += "inner join Unit u on p.prodUnit=u.unitId ";
            sql += "inner join Supplier s on p.supplierId=s.supplierId ";
            sql += " where prodid not in (";
            sql += "select top " + startRow + " prodid from Product)";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ProductVo p = new ProductVo();
                p.setProdid(rs.getString("prodid").trim());
                p.setProdname(rs.getString("prodname").trim());
                p.setProdModel(rs.getString("prodModel").trim());
                p.setProdUnit(rs.getInt("prodUnit"));
                p.setProdStyle(rs.getString("prodStyle").trim());

                p.setProdCount(rs.getInt("prodCount"));

                p.setInPrice(rs.getFloat("inPrice"));
                p.setSalePrice(rs.getFloat("salePrice"));
                p.setLowSalePrice(rs.getFloat("lowSalePrice"));
                p.setProdSerial(rs.getString("prodSerial").trim());
                p.setCdKey(rs.getString("cdKey").trim());
                p.setSaleStatus(rs.getString("saleStatus").trim());
                p.setSupplierId(rs.getInt("supplierId"));
                p.setRemark(rs.getString("remark"));
                p.setProdUnitName(rs.getString("unitName").trim());
                p.setSupplierName(rs.getString("supplierName").trim());
                list.add(p);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //修改商品状态
    public boolean updateSaleStatus(String prodid) {
        Connection conn = DBPool.openDB();
        boolean b = false;
        try {
            String sql = "select saleStatus from Product where prodid='" + prodid + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("saleStatus").equals("未售")) {
                    b = true;
                } else {
                    b = false;
                }
            }
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    //以售/未售
    public void updateSaleStatus(String prodid, String saleStatus) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update Product set saleStatus='" + saleStatus + "'  where prodid='" + prodid + "'";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //客户表
    //新建客户
    public void addCustomerInfo(CustomerInfoVo c) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "insert into CustomerInfo values('";
            sql += c.getCustname() + "','";
            sql += c.getCusttype() + "','";
            sql += c.getBankAccount() + "','";
            sql += c.getBankName() + "','";
            sql += c.getContact() + "','";
            sql += c.getPhone() + "','";
            sql += c.getTicketName() + "','";
            sql += c.getTicketAddr() + "','";
            sql += c.getTicketTel() + "','";
            sql += c.getTaxNo() + "','";
            sql += c.getCustState() + "',";
            sql += c.getUserid() + ",'";
            sql += c.getSource() + "' )";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据客户资料id查找
    public CustomerInfoVo findCustomerInfoId(int custId) {
        CustomerInfoVo c = new CustomerInfoVo();
        Connection conn = DBPool.openDB();
        try {
            String sql = "select * from CustomerInfo where custId=" + custId;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                c.setCustId(rs.getInt("custId"));
                c.setCustname(rs.getString("custname").trim());
                c.setCusttype(rs.getString("custtype").trim());
                c.setBankAccount(rs.getString("bankAccount").trim());
                c.setBankName(rs.getString("bankName").trim());
                c.setContact(rs.getString("Contact").trim());
                c.setPhone(rs.getString("Phone").trim());
                c.setTicketName(rs.getString("TicketName").trim());
                c.setTicketAddr(rs.getString("TicketAddr").trim());
                c.setTicketTel(rs.getString("TicketTel").trim());
                c.setTaxNo(rs.getString("TaxNo").trim());
                c.setCustState(rs.getString("custState").trim());
                c.setUserid(rs.getInt("userid"));
                c.setSource(rs.getString("source").trim());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    //修改客户资料
    public void updateCustomerInfo(CustomerInfoVo c) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update CustomerInfo set custname='";
            sql += c.getCustname() + "',custtype='";
            sql += c.getCusttype() + "',bankAccount='";
            sql += c.getBankAccount() + "',bankName='";
            sql += c.getBankName() + "',Contact='";
            sql += c.getContact() + "',Phone='";
            sql += c.getPhone() + "',TicketName='";
            sql += c.getTicketName() + "',TicketAddr='";
            sql += c.getTicketAddr() + "',TicketTel='";
            sql += c.getTicketTel() + "',TaxNo='";
            sql += c.getTaxNo() + "',custState='";
            sql += c.getCustState() + "',userid=";
            sql += c.getUserid() + ",source='";
            sql += c.getSource() + "' where custId=" + c.getCustId();
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除客户资料
    public void depCustomerInfo(int custId) {
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete from CustomerInfo where custId=" + custId;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询客户资料录数
    public int findCountCustomerInfo() {
        int count = 0;
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from CustomerInfo";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return count;
    }

    //查找客户资料分页
    public List<CustomerInfoVo> findPageCustomerInfo(int startRow, int pageSize) {
        List<CustomerInfoVo> list = new ArrayList<CustomerInfoVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " c.*,u.username  from CustomerInfo c ";
            sql += "inner join Users u on c.userid=u.userid ";
            sql += " where custId not in (";
            sql += "select top " + startRow + " custId from CustomerInfo)";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CustomerInfoVo c = new CustomerInfoVo();
                c.setCustId(rs.getInt("custId"));
                c.setCustname(rs.getString("custname").trim());
                c.setCusttype(rs.getString("custtype").trim());
                c.setBankAccount(rs.getString("bankAccount").trim());
                c.setBankName(rs.getString("bankName").trim());
                c.setContact(rs.getString("Contact").trim());
                c.setPhone(rs.getString("Phone").trim());
                c.setTicketName(rs.getString("TicketName").trim());
                c.setTicketAddr(rs.getString("TicketAddr").trim());
                c.setTicketTel(rs.getString("TicketTel").trim());
                c.setTaxNo(rs.getString("TaxNo").trim());
                c.setCustState(rs.getString("custState").trim());
                c.setUserid(rs.getInt("userid"));
                c.setSource(rs.getString("source").trim());
                c.setUsername(rs.getString("username").trim());
                list.add(c);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //查找用户
    public List<UsersVo> findUsers() {
        List<UsersVo> list = new ArrayList<UsersVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from Users";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                UsersVo u = new UsersVo();
                u.setUserid(rs.getInt("userid"));
                u.setUsername(rs.getString("username").trim());
                list.add(u);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //查找客户
    public List<CustomerInfoVo> findCustomerInfos() {
        List<CustomerInfoVo> list = new ArrayList<CustomerInfoVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from CustomerInfo";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CustomerInfoVo c = new CustomerInfoVo();
                c.setCustId(rs.getInt("custId"));
                c.setCustname(rs.getString("custname").trim());
                list.add(c);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //合同表
    //新建合同
    public void addContract(ContractVo c) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "insert into Contract values('";
            sql += c.getContract_no() + "',";
            sql += c.getCustId() + ",'";
            sql += c.getContract_time() + "','";
            sql += c.getDue_time() + "',";
            sql += c.getTotal_money() + ",'";
            sql += c.getDeposit() + "','";
            sql += c.getPay_type() + "','";
            sql += c.getStatus() + "',";
            sql += c.getEmpid() + ",";
            sql += c.getOperator() + ",'";
            sql += c.getOprtime() + "','";
            sql += c.getContract_name() + "','";
            sql += c.getRemark() + "' )";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据合同表id查找
    public ContractVo findContractId(int contract_id) {
        ContractVo c = new ContractVo();
        Connection conn = DBPool.openDB();
        try {
            String sql = "select * from Contract where contract_id=" + contract_id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                c.setContract_id(rs.getInt("contract_id"));
                c.setContract_no(rs.getString("contract_no").trim());
                c.setCustId(rs.getInt("custId"));
                c.setContract_time(ContextUtils.dateToStrShort(rs.getTimestamp("contract_time")));
                c.setDue_time(ContextUtils.dateToStrShort(rs.getTimestamp("due_time")));
                c.setTotal_money(rs.getFloat("total_money"));
                c.setDeposit(rs.getString("deposit"));
                c.setPay_type(rs.getString("pay_type").trim());
                c.setStatus(rs.getString("status").trim());
                c.setEmpid(rs.getInt("empid"));
                c.setOperator(rs.getInt("operator"));
                c.setOprtime(ContextUtils.dateToStrLong(rs.getTimestamp("oprtime")));
                c.setContract_name(rs.getString("contract_name").trim());
                c.setRemark(rs.getString("remark").trim());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    //修改合同表
    public void updateContract(ContractVo c) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update Contract set contract_no='";
            sql += c.getContract_no() + "',custId=";
            sql += c.getCustId() + ",contract_time='";
            sql += c.getContract_time() + "',due_time='";
            sql += c.getDue_time() + "',total_money=";
            sql += c.getTotal_money() + ",deposit='";
            sql += c.getDeposit() + "',pay_type='";
            sql += c.getPay_type() + "',status='";
            sql += c.getStatus() + "',empid=";
            sql += c.getEmpid() + ",operator=";
            sql += c.getOperator() + ",oprtime=' ";
            sql += c.getOprtime() + "', contract_name='";
            sql += c.getContract_name() + "', remark='";
            sql += c.getRemark() + "' ";
            sql += "where contract_id=" + c.getContract_id();
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除合同表
    public void depContract(int contract_id) {
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete from Contract where contract_id=" + contract_id;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询合同记录数
    public int findCountContract(String custId) {
        int count = 0;
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from Contract where custId=" + custId;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return count;
    }

    //查找合同表分页
    public List<ContractVo> findPageContract(int startRow, int pageSize, String custId) {
        List<ContractVo> list = new ArrayList<ContractVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " c.*,u.username,(u1.username)usernames,c1.custname  from Contract c ";
            sql += "inner join Users u on c.empid=u.userid ";
            sql += "inner join Users u1 on c.operator=u1.userid ";
            sql += "inner join CustomerInfo c1 on c1.custId=c.custId ";
            sql += " where contract_id not in (";
            sql += "select top " + startRow + " contract_id from Contract) and c.custId=" + custId;
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ContractVo c = new ContractVo();
                c.setContract_id(rs.getInt("contract_id"));
                c.setContract_no(rs.getString("contract_no").trim());
                c.setCustname(rs.getString("custname"));
                c.setContract_time(ContextUtils.dateToStrShort(rs.getTimestamp("contract_time")));
                c.setDue_time(ContextUtils.dateToStrShort(rs.getTimestamp("due_time")));
                c.setTotal_money(rs.getFloat("total_money"));
                c.setDeposit(rs.getString("deposit"));
                c.setPay_type(rs.getString("pay_type").trim());
                c.setStatus(rs.getString("status").trim());
                c.setEmpName(rs.getString("username"));
                c.setOperatorName(rs.getString("usernames"));
                c.setOprtime(ContextUtils.dateToStrShort(rs.getTimestamp("oprtime")));
                c.setContract_name(rs.getString("contract_name").trim());
                c.setRemark(rs.getString("remark").trim());
                list.add(c);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //查询部门人数
    public List<DepVos> findCountDeps() {
        Connection conn = DBPool.openDB();
        List<DepVos> list = new ArrayList<DepVos>();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*)cnt,depname from Dep d inner join Users u on u.depid=d.depid ";
            sql += "group by depname";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                DepVos d = new DepVos();
                d.setCount(rs.getInt("cnt"));
                d.setDepname(rs.getString("depname"));
                list.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    //查找合同表
    public List<ContractVo> findContract() {
        List<ContractVo> list = new ArrayList<ContractVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from Contract";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ContractVo u = new ContractVo();
                u.setContract_id(rs.getInt("contract_id"));
                u.setContract_no(rs.getString("contract_no").trim());
                list.add(u);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //合同扫描附件表
    //新合同扫描附件
    public void addContractAttach(ContractAttachVo c) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "insert into ContractAttach values(";
            sql += c.getContract_id() + ",";
            sql += c.getSeq() + ",'";
            sql += c.getAttachFile() + "',";
            sql += "getdate(),";
            sql += c.getUser_name() + ",'";
            sql += c.getAttachURL() + "' )";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据合同扫描附件id查找
    public ContractAttachVo findContractAttachId(int contractAttach_id) {
        ContractAttachVo c = new ContractAttachVo();
        Connection conn = DBPool.openDB();
        try {
            String sql = "select * from ContractAttach where contractAttach_id=" + contractAttach_id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                c.setContractAttach_id(rs.getInt("contractAttach_id"));
                c.setContract_id(rs.getInt("contract_id"));
                c.setSeq(rs.getInt("Seq"));
                c.setAttachFile(rs.getString("AttachFile").trim());
                c.setUploadTime(ContextUtils.dateToStrShort(rs.getTimestamp("UploadTime")));
                c.setUser_name(rs.getInt("user_name"));
                c.setAttachURL(rs.getString("attachURL").trim());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    //修改合同扫描附件
    public void updateContractAttach(ContractAttachVo c) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update ContractAttach set contract_id=";
            sql += c.getContract_id() + ",Seq=";
            sql += c.getSeq() + ",AttachFile='";
            sql += c.getAttachFile() + "',UploadTime=";
            sql += "getdate(),user_name=";
            sql += c.getUser_name() + ",attachURL='";
            sql += c.getAttachURL() + "' where contractAttach_id=" + c.getContractAttach_id();
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除合同扫描附件
    public void depContractAttach(int contractAttach_id) {
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete from ContractAttach where contractAttach_id=" + contractAttach_id;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询合同扫描附件记录数
    public int findCountContractAttach(String contract_id) {
        int count = 0;
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from ContractAttach where contract_id=" + contract_id;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return count;
    }

    //查找合同扫描附件分页
    public List<ContractAttachVo> findPageContractAttach(int startRow, int pageSize, String contract_id) {
        List<ContractAttachVo> list = new ArrayList<ContractAttachVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " c.*,c1.contract_no,c1.contract_name,u.username from ContractAttach c ";
            sql += "inner join Contract c1 on c1.contract_id=c.contract_id ";
            sql += "inner join Users u on u.userid=c.user_name";
            sql += " where contractAttach_id not in (";
            sql += "select top " + startRow + " contractAttach_id from ContractAttach) ";
            sql += " and c.contract_id=" + contract_id;
            sql += " order by c.Seq";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ContractAttachVo c = new ContractAttachVo();
                c.setContractAttach_id(rs.getInt("contractAttach_id"));
                c.setContract_id(rs.getInt("contract_id"));
                c.setSeq(rs.getInt("Seq"));
                c.setAttachFile(rs.getString("AttachFile").trim());
                c.setUploadTime(ContextUtils.dateToStrShort(rs.getTimestamp("UploadTime")));
                c.setUser_name(rs.getInt("user_name"));
                c.setAttachURL(rs.getString("attachURL").trim());
                c.setContract_on(rs.getString("contract_no").trim());
                c.setContract_name(rs.getString("contract_name").trim());
                c.setUserName(rs.getString("username").trim());
                list.add(c);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    //派工单表
    //新增派工单表
    public void addJobRecord(JobRecordVo j) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "insert into JobRecord values('";
            sql += j.getOrderId() + "','";
            sql += j.getJobDate() + "','";
            sql += j.getProdid() + "',";
            sql += j.getCustid() + ",'";
            sql += j.getJobContent() + "','";
            sql += j.getCallback() + "','";
            sql += j.getUserid() + "','";
            sql += j.getCustEval() + "','";
            sql += j.getCustSign() + "','";
            sql += j.getStartTime() + "','";
            sql += j.getEndTime() + "',";
            sql += j.getWorkDay() + ",";
            sql += j.getBusMoney() + ",";
            sql += j.getAttachMoney() + " )";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据派工单id查找
    public JobRecordVo findJobRecordId(int jobId) {
        JobRecordVo j = new JobRecordVo();
        Connection conn = DBPool.openDB();
        try {
            String sql = "select * from JobRecord where jobId=" + jobId;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                j.setJobId(rs.getInt("jobId"));
                j.setOrderId(rs.getString("orderId").trim());
                j.setJobDate(ContextUtils.dateToStrShort(rs.getTimestamp("jobDate")));
                j.setProdid(rs.getString("prodid").trim());
                j.setCustid(rs.getInt("custid"));
                j.setJobContent(rs.getString("jobContent").trim());
                j.setCallback(rs.getString("callback").trim());
                j.setUserid(rs.getString("userid").trim());
                j.setCustEval(rs.getString("custEval").trim());
                j.setCustSign(rs.getString("custSign").trim());
                j.setStartTime(ContextUtils.dateToStrShort(rs.getTimestamp("startTime")));
                j.setEndTime(ContextUtils.dateToStrShort(rs.getTimestamp("endTime")));
                j.setWorkDay(rs.getInt("workDay"));
                j.setBusMoney(rs.getFloat("busMoney"));
                j.setAttachMoney(rs.getFloat("attachMoney"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    //修改派工单
    public void updateJobRecord(JobRecordVo j) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update JobRecord set orderId='";
            sql += j.getOrderId() + "',jobDate='";
            sql += j.getJobDate() + "',prodid='";
            sql += j.getProdid() + "',custid=";
            sql += j.getCustid() + ",jobContent='";
            sql += j.getJobContent() + "',callback='";
            sql += j.getCallback() + "',userid='";
            sql += j.getUserid() + "',custEval='";
            sql += j.getCustEval() + "',custSign='";
            sql += j.getCustSign() + "',startTime='";
            sql += j.getStartTime() + "',endTime='";
            sql += j.getEndTime() + "',workDay=";
            sql += j.getWorkDay() + ",busMoney=";
            sql += j.getBusMoney() + ",attachMoney=";
            sql += j.getAttachMoney() + " where jobId=" + j.getJobId();
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除派工单表
    public void depJobRecord(int jobId) {
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete from JobRecord where jobId=" + jobId;
            stmt.executeUpdate(sql);
            stmt.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询派工单记录数
    public int findCountJobRecord(String custid) {
        int count = 0;
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from JobRecord where custid=" + custid;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return count;
    }

    //查找派工单分页
    public List<JobRecordVo> findPageJobRecord(int startRow, int pageSize, String custid) {
        List<JobRecordVo> list = new ArrayList<JobRecordVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " j.*,p.prodname,c.custname,u.username  from JobRecord j ";
            sql += "inner join Product p on p.prodid=j.prodid ";
            sql += "inner join CustomerInfo c on c.custId=j.custid ";
            sql += " inner join Users u on u.userid=j.userid ";
            sql += " where jobId not in (";
            sql += "select top " + startRow + " jobId from JobRecord)";
            sql += " and j.custid=" + custid;
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                JobRecordVo j = new JobRecordVo();
                j.setJobId(rs.getInt("jobId"));
                j.setOrderId(rs.getString("orderId").trim());
                j.setJobDate(ContextUtils.dateToStrShort(rs.getTimestamp("jobDate")));
                j.setProdid(rs.getString("prodid").trim());
                j.setCustid(rs.getInt("custid"));
                j.setJobContent(rs.getString("jobContent").trim());
                j.setCallback(rs.getString("callback").trim());
                j.setUserid(rs.getString("userid").trim());
                j.setCustEval(rs.getString("custEval").trim());
                j.setCustSign(rs.getString("custSign").trim());
                j.setStartTime(ContextUtils.dateToStrShort(rs.getTimestamp("startTime")));
                j.setEndTime(ContextUtils.dateToStrShort(rs.getTimestamp("endTime")));
                j.setWorkDay(rs.getInt("workDay"));
                j.setBusMoney(rs.getFloat("busMoney"));
                j.setAttachMoney(rs.getFloat("attachMoney"));

                j.setProdName(rs.getString("prodname").trim());
                j.setCustname(rs.getString("custname").trim());
                j.setUsername(rs.getString("username").trim());
                list.add(j);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //查找派工单分页
    public List<JobRecordVo> findPageJobRecords(int startRow, int pageSize, String
            customerInfoVos, String usersVos, String stime, String etime) {
        List<JobRecordVo> list = new ArrayList<JobRecordVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " j.*,p.prodname,c.custname,u.username  from JobRecord j ";
            sql += "inner join Product p on p.prodid=j.prodid ";
            sql += "inner join CustomerInfo c on c.custId=j.custid ";
            sql += " inner join Users u on u.userid=j.userid ";
            sql += " where jobId not in (";
            sql += "select top " + startRow + " jobId from JobRecord) and 1=1 ";
            if (customerInfoVos != null) {
                if (!customerInfoVos.equals("0")) {
                    sql += " and j.custid=" + customerInfoVos;
                }
                if (!usersVos.equals("0")) {
                    sql += " and j.userid='" + usersVos + "' ";
                }
                if (stime != null) {
                    if (!stime.equals("")) {
                        sql += " and startTime between '" + stime + " 00:00' and '" + etime + " 23:59'";
                    }
                }
            }


            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                JobRecordVo j = new JobRecordVo();
                j.setJobId(rs.getInt("jobId"));
                j.setOrderId(rs.getString("orderId").trim());
                j.setJobDate(ContextUtils.dateToStrShort(rs.getTimestamp("jobDate")));
                j.setProdid(rs.getString("prodid").trim());
                j.setCustid(rs.getInt("custid"));
                j.setJobContent(rs.getString("jobContent").trim());
                j.setCallback(rs.getString("callback").trim());
                j.setUserid(rs.getString("userid").trim());
                j.setCustEval(rs.getString("custEval").trim());
                j.setCustSign(rs.getString("custSign").trim());
                j.setStartTime(ContextUtils.dateToStrShort(rs.getTimestamp("startTime")));
                j.setEndTime(ContextUtils.dateToStrShort(rs.getTimestamp("endTime")));
                j.setWorkDay(rs.getInt("workDay"));
                j.setBusMoney(rs.getFloat("busMoney"));
                j.setAttachMoney(rs.getFloat("attachMoney"));
                j.setUsername(rs.getString("username").trim());
                j.setProdName(rs.getString("prodname").trim());
                j.setCustname(rs.getString("custname").trim());
                list.add(j);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //商务洽谈表
    //新增商务洽谈表
    public void addBusiness(BusinessVo b) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "insert into Business values('";
            sql += b.getBusDate() + "','";
            sql += b.getProdid() + "','";
            sql += b.getChatContent() + "','";
            sql += b.getChatResult() + "',";
            sql += b.getCustid() + ",'";
            sql += b.getCustContact() + "','";
            sql += b.getPhone() + "',";
            sql += b.getUserid() + ",'";
            sql += b.getModule() + "','";
            sql += b.getModuleState() + "',";
            sql += b.getModuleMoney() + ",'";
            sql += b.getRemark() + "' )";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据商务洽谈表id查找
    public BusinessVo findBusinessId(int businessId) {
        BusinessVo b = new BusinessVo();
        Connection conn = DBPool.openDB();
        try {
            String sql = "select * from Business where businessId=" + businessId;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                b.setBusinessId(rs.getInt("businessId"));
                b.setBusDate(ContextUtils.dateToStrShort(rs.getTimestamp("busDate")));
                b.setProdid(rs.getString("prodid").trim());
                b.setChatContent(rs.getString("chatContent").trim());
                b.setChatResult(rs.getString("chatResult"));
                b.setCustid(rs.getInt("custid"));
                b.setCustContact(rs.getString("custContact").trim());
                b.setPhone(rs.getString("phone").trim());
                b.setUserid(rs.getInt("userid"));
                b.setModule(rs.getString("module").trim());
                b.setModuleState(rs.getString("moduleState").trim());
                b.setModuleMoney(rs.getFloat("moduleMoney"));
                b.setRemark(rs.getString("remark").trim());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    //修改商务洽谈表
    public void updateBusiness(BusinessVo b) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update Business set busDate='";
            sql += b.getBusDate() + "',prodid='";
            sql += b.getProdid() + "',chatContent='";
            sql += b.getChatContent() + "',chatResult='";
            sql += b.getChatResult() + "',custid=";
            sql += b.getCustid() + ",custContact='";
            sql += b.getCustContact() + "',phone='";
            sql += b.getPhone() + "',userid=";
            sql += b.getUserid() + ",module='";
            sql += b.getModule() + "',moduleState='";
            sql += b.getModuleState() + "',moduleMoney=";
            sql += b.getModuleMoney() + ",remark='";
            sql += b.getRemark() + "' where businessId=" + b.getBusinessId();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除商务洽谈表
    public void depBusiness(int businessId) {
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete from Business where businessId=" + businessId;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    //查询商务洽谈表记录数
    public int findCountBusiness(String custId) {
        int count = 0;
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from Business  where custid='" + custId + "'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return count;
    }

    //查找商务洽谈表分页
    public List<BusinessVo> findPageBusiness(int startRow, int pageSize, String custid) {
        List<BusinessVo> list = new ArrayList<BusinessVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " b.*,p.prodname,c.custname  from Business b ";
            sql += "inner join Product p on p.prodid=b.prodid ";
            sql += "inner join CustomerInfo c on c.custId=b.custid ";
            sql += " where businessId not in (";
            sql += "select top " + startRow + " businessId from Business) ";
            sql += " and b.custid=" + custid;
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BusinessVo b = new BusinessVo();
                b.setBusinessId(rs.getInt("businessId"));
                b.setBusDate(ContextUtils.dateToStrShort(rs.getTimestamp("busDate")));
                b.setProdid(rs.getString("prodid").trim());
                b.setChatContent(rs.getString("chatContent").trim());
                b.setChatResult(rs.getString("chatResult"));
                b.setCustid(rs.getInt("custid"));
                b.setCustContact(rs.getString("custContact").trim());
                b.setPhone(rs.getString("phone").trim());
                b.setUserid(rs.getInt("userid"));
                b.setModule(rs.getString("module").trim());
                b.setModuleState(rs.getString("moduleState").trim());
                b.setModuleMoney(rs.getFloat("moduleMoney"));
                b.setRemark(rs.getString("remark").trim());

                b.setProdname(rs.getString("prodname").trim());
                b.setCustname(rs.getString("custname").trim());
                list.add(b);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //客户联系表
    //新增客户联系表
    public void addContact(ContactVo c) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "insert into Contact values('";
            sql += c.getTalkDate() + "','";
            sql += c.getCustContact() + "','";
            sql += c.getPhone1() + "','";
            sql += c.getPhone2() + "',";
            sql += c.getCustid() + ",'";
            sql += c.getQqCode() + "','";
            sql += c.getEmail() + "','";
            sql += c.getWeixin() + "',";
            sql += c.getUserid() + ",'";
            sql += c.getBirthday() + "','";
            sql += c.getHobbit() + "','";
            sql += c.getJobName() + "','";
            sql += c.getRemark() + "' )";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据客户联系表id查找
    public ContactVo findContactId(int contactId) {
        ContactVo c = new ContactVo();
        Connection conn = DBPool.openDB();
        try {
            String sql = "select * from Contact where contactId=" + contactId;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                c.setContactId(rs.getInt("contactId"));
                c.setTalkDate(ContextUtils.dateToStrShort(rs.getTimestamp("talkDate")));
                c.setCustContact(rs.getString("custContact").trim());
                c.setPhone1(rs.getString("phone1").trim());
                c.setPhone2(rs.getString("phone1").trim());
                c.setCustid(rs.getInt("custid"));
                c.setQqCode(rs.getString("qqCode").trim());
                c.setEmail(rs.getString("email").trim());
                c.setWeixin(rs.getString("weixin").trim());
                c.setUserid(rs.getInt("userid"));
                c.setBirthday(rs.getString("birthday").trim());
                c.setHobbit(rs.getString("hobbit").trim());
                c.setJobName(rs.getString("jobName").trim());
                c.setRemark(rs.getString("remark").trim());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    //修改客户联系表
    public void updateContact(ContactVo c) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update Contact set talkDate='";
            sql += c.getTalkDate() + "',custContact='";
            sql += c.getCustContact() + "',phone1='";
            sql += c.getPhone1() + "',phone2='";
            sql += c.getPhone2() + "',custid=";
            sql += c.getCustid() + ",qqCode='";
            sql += c.getQqCode() + "',email='";
            sql += c.getEmail() + "',weixin='";
            sql += c.getWeixin() + "',userid=";
            sql += c.getUserid() + ",birthday='";
            sql += c.getBirthday() + "',hobbit='";
            sql += c.getHobbit() + "',jobName='";
            sql += c.getJobName() + "',remark='";
            sql += c.getRemark() + "' where contactId=" + c.getContactId();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查找客户联系表分页
    public List<ContactVo> findPageContact(int startRow, int pageSize, String custId) {
        List<ContactVo> list = new ArrayList<ContactVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " c.*,u.username,c1.custname  from Contact c ";
            sql += "inner join Users u on u.userid=c.userid ";
            sql += "inner join CustomerInfo c1 on c.custid=c1.custId ";
            sql += " where contactId not in (";
            sql += "select top " + startRow + " contactId from Contact)";
            sql += " and c.custId=" + custId;
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ContactVo c = new ContactVo();
                c.setContactId(rs.getInt("contactId"));
                c.setTalkDate(ContextUtils.dateToStrShort(rs.getTimestamp("talkDate")));
                c.setCustContact(rs.getString("custContact").trim());
                c.setPhone1(rs.getString("phone1").trim());
                c.setPhone2(rs.getString("phone1").trim());
                c.setCustid(rs.getInt("custid"));
                c.setQqCode(rs.getString("qqCode").trim());
                c.setEmail(rs.getString("email").trim());
                c.setWeixin(rs.getString("weixin").trim());
                c.setUserid(rs.getInt("userid"));
                c.setBirthday(rs.getString("birthday").trim());
                c.setHobbit(rs.getString("hobbit").trim());
                c.setJobName(rs.getString("jobName").trim());
                c.setRemark(rs.getString("remark").trim());
                c.setCustname(rs.getString("custname").trim());
                c.setUsername(rs.getString("username").trim());

                list.add(c);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //收款方式表
    //新增收款方式表
    public void addPaidType(PaidTypeVo p) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "insert into PaidType values('";
            sql += p.getPaidtypename() + "' )";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据收款方式表id查找
    public PaidTypeVo findPaidTypeId(int paidtypeid) {
        PaidTypeVo p = new PaidTypeVo();
        Connection conn = DBPool.openDB();
        try {
            String sql = "select * from PaidType where paidtypeid=" + paidtypeid;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                p.setPaidtypeid(rs.getInt("paidtypeid"));
                p.setPaidtypename(rs.getString("paidtypename").trim());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    //修改收款方式表
    public void updatePaidType(PaidTypeVo p) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update PaidType set paidtypename='";
            sql += p.getPaidtypename() + "' where paidtypeid=" + p.getPaidtypeid();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查找收款方式表分页
    public List<PaidTypeVo> findPagePaidType(int startRow, int pageSize) {
        List<PaidTypeVo> list = new ArrayList<PaidTypeVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + "  * from PaidType ";
            sql += " where paidtypeid not in (";
            sql += "select top " + startRow + " paidtypeid from PaidType)";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                PaidTypeVo p = new PaidTypeVo();
                p.setPaidtypeid(rs.getInt("paidtypeid"));
                p.setPaidtypename(rs.getString("paidtypename").trim());
                list.add(p);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //收款管理表
    //新增收款管理表
    public void addFinance(FinanceVo f) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "insert into Finance values('";
            sql += f.getOrderId() + "','";
            sql += f.getProdid() + "',";
            sql += f.getPaidtypeid() + ",";
            sql += f.getRemainMoney() + ",";
            sql += f.getPaidMoney() + ",";
            sql += f.getOrderMoney() + ",'";
            sql += f.getPaidPerson() + "','";
            sql += f.getInbank() + "','";
            sql += f.getBankAccount() + "','";
            sql += f.getOutbank() + "','";
            sql += f.getWarrant() + "','";
            sql += f.getPaidTime() + "','";
            sql += f.getPaidinTime() + "','";
            sql += f.getInvalid() + "',";
            sql += f.getUserid() + ",'";
            sql += f.getOprtime() + "','";
            sql += f.getOprType() + "' )";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据收款管理表id查找
    public FinanceVo findFinanceId(int financeId) {
        FinanceVo f = new FinanceVo();
        Connection conn = DBPool.openDB();
        try {
            String sql = "select * from Finance where financeId=" + financeId;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                f.setFinanceId(rs.getInt("financeId"));
                f.setOrderId(rs.getString("orderId").trim());
                f.setProdid(rs.getString("prodid").trim());
                f.setPaidtypeid(rs.getInt("paidtypeid"));
                f.setRemainMoney(rs.getFloat("remainMoney"));
                f.setPaidMoney(rs.getFloat("paidMoney"));
                f.setOrderMoney(rs.getFloat("orderMoney"));
                f.setPaidPerson(rs.getString("paidPerson").trim());
                f.setInbank(rs.getString("inbank").trim());
                f.setBankAccount(rs.getString("bankAccount").trim());
                f.setOutbank(rs.getString("outbank").trim());
                f.setWarrant(rs.getString("warrant"));
                f.setPaidTime(ContextUtils.dateToStrShort(rs.getTimestamp("paidTime")));
                f.setPaidinTime(ContextUtils.dateToStrLong(rs.getTimestamp("paidinTime")));
                f.setInvalid(rs.getString("invalid"));
                f.setUserid(rs.getInt("userid"));
                f.setOprtime(ContextUtils.dateToStrLong(rs.getTimestamp("oprtime")));
                f.setOprType(rs.getString("oprType"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    //修改收款管理表
    public void updateFinance(FinanceVo f) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update Finance set orderId='";
            sql += f.getOrderId() + "',prodid='";
            sql += f.getProdid() + "',paidtypeid=";
            sql += f.getPaidtypeid() + ",remainMoney=";
            sql += f.getRemainMoney() + ",paidMoney=";
            sql += f.getPaidMoney() + ",orderMoney=";
            sql += f.getOrderMoney() + ",paidPerson='";
            sql += f.getPaidPerson() + "',inbank='";
            sql += f.getInbank() + "',bankAccount='";
            sql += f.getBankAccount() + "',outbank='";
            sql += f.getOutbank() + "',warrant='";
            sql += f.getWarrant() + "',paidTime='";
            sql += f.getPaidTime() + "',paidinTime='";
            sql += f.getPaidinTime() + "',invalid='";
            sql += f.getInvalid() + "',userid=";
            sql += f.getUserid() + ",oprtime='";
            sql += f.getOprtime() + "',oprType='";
            sql += f.getOprType() + "' where financeId=" + f.getFinanceId();
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查找收款管理表分页
    public List<FinanceVo> findPageFinance(int startRow, int pageSize) {
        List<FinanceVo> list = new ArrayList<FinanceVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + "  f.*,p.paidtypename,p1.prodname from Finance f ";
            sql += "inner join PaidType p on f.paidtypeid=p.paidtypeid ";
            sql += "inner join Product p1 on f.prodid=p1.prodid ";
            sql += " where financeId not in (";
            sql += "select top " + startRow + " financeId from Finance)";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                FinanceVo f = new FinanceVo();
                f.setFinanceId(rs.getInt("financeId"));
                f.setOrderId(rs.getString("orderId").trim());
                f.setProdid(rs.getString("prodid").trim());
                f.setPaidtypeid(rs.getInt("paidtypeid"));
                f.setRemainMoney(rs.getFloat("remainMoney"));
                f.setPaidMoney(rs.getFloat("paidMoney"));
                f.setOrderMoney(rs.getFloat("orderMoney"));
                f.setPaidPerson(rs.getString("paidPerson").trim());
                f.setInbank(rs.getString("inbank").trim());
                f.setBankAccount(rs.getString("bankAccount").trim());
                f.setOutbank(rs.getString("outbank").trim());
                f.setWarrant(rs.getString("warrant"));
                f.setPaidTime(ContextUtils.dateToStrShort(rs.getTimestamp("paidTime")));
                f.setPaidinTime(ContextUtils.dateToStrLong(rs.getTimestamp("paidinTime")));
                f.setInvalid(rs.getString("invalid"));
                f.setUserid(rs.getInt("userid"));
                f.setOprtime(ContextUtils.dateToStrLong(rs.getTimestamp("oprtime")));
                f.setOprType(rs.getString("oprType"));
                f.setProdname(rs.getString("prodname").trim());
                f.setPaidtypename(rs.getString("paidtypename").trim());
                list.add(f);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //开票信息表
    //新增开票信息表
    public void addTicket(TicketVo t) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "insert into Ticket values('";
            sql += t.getTicketDate() + "','";
            sql += t.getOrderid() + "',";
            sql += t.getCustid() + ",";
            sql += t.getTicketMoney() + ",'";
            sql += t.getTicketComp() + "',";
            sql += t.getUserid() + ",'";
            sql += t.getOprtime() + "','";
            sql += t.getRemark() + "' )";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据开票信息表id查找
    public TicketVo findTicketId(int id) {
        TicketVo t = new TicketVo();
        Connection conn = DBPool.openDB();
        try {
            String sql = "select * from Ticket where id=" + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                t.setId(rs.getInt("id"));
                t.setTicketDate(ContextUtils.dateToStrShort(rs.getTimestamp("ticketDate")));
                t.setOrderid(rs.getString("orderid").trim());
                t.setCustid(rs.getInt("custid"));
                t.setTicketMoney(rs.getFloat("ticketMoney"));
                t.setTicketComp(rs.getString("ticketComp").trim());
                t.setUserid(rs.getInt("userid"));
                t.setOprtime(ContextUtils.dateToStrLong(rs.getTimestamp("oprtime")));
                t.setRemark(rs.getString("remark"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    //修改开票信息表
    public void updateTicket(TicketVo t) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update Ticket set ticketDate='";
            sql += t.getTicketDate() + "',orderid='";
            sql += t.getOrderid() + "',custid=";
            sql += t.getCustid() + ",ticketMoney=";
            sql += t.getTicketMoney() + ",ticketComp='";
            sql += t.getTicketComp() + "',userid=";
            sql += t.getUserid() + ",oprtime='";
            sql += t.getOprtime() + "',remark='";
            sql += t.getRemark() + "' where id=" + t.getId();
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查找开票信息表分页
    public List<TicketVo> findPageTicket(int startRow, int pageSize) {
        List<TicketVo> list = new ArrayList<TicketVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + "  t.*,u.username,c.custname from Ticket t ";
            sql += "inner join Users u on t.userid=u.userid ";
            sql += "inner join CustomerInfo c on t.custid=c.custId ";
            sql += " where id not in (";
            sql += "select top " + startRow + " id from Ticket)";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TicketVo t = new TicketVo();
                t.setId(rs.getInt("id"));
                t.setTicketDate(ContextUtils.dateToStrShort(rs.getTimestamp("ticketDate")));
                t.setOrderid(rs.getString("orderid").trim());
                t.setCustid(rs.getInt("custid"));
                t.setTicketMoney(rs.getFloat("ticketMoney"));
                t.setTicketComp(rs.getString("ticketComp").trim());
                t.setUserid(rs.getInt("userid"));
                t.setOprtime(ContextUtils.dateToStrLong(rs.getTimestamp("oprtime")));
                t.setRemark(rs.getString("remark"));
                t.setCustname(rs.getString("custname"));
                t.setUsername(rs.getString("username"));
                list.add(t);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //工作周报表
    //新增工作周报表
    public void addWeeklyReport(WeeklyReportVo w) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "insert into WeeklyReport values('";
            sql += w.getWeekDate() + "','";
            sql += w.getWorkContent() + "','";
            sql += w.getWorkReview() + "','";
            sql += w.getQuestion() + "','";
            sql += w.getWarning() + "','";
            sql += w.getWeekPlan() + "',";
            sql += w.getUserid() + ",'";
            sql += w.getOprtime() + "','";
            sql += w.getRemark() + "' )";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据工作周报表id查找
    public WeeklyReportVo findWeeklyReportId(int id) {
        WeeklyReportVo w = new WeeklyReportVo();
        Connection conn = DBPool.openDB();
        try {
            String sql = "select * from WeeklyReport where id=" + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                w.setId(rs.getInt("id"));
                w.setWeekDate(ContextUtils.dateToStrShort(rs.getTimestamp("weekDate")));
                w.setWorkContent(rs.getString("workContent").trim());
                w.setWorkReview(rs.getString("workReview").trim());
                w.setQuestion(rs.getString("question").trim());
                w.setWarning(rs.getString("warning").trim());
                w.setWeekPlan(rs.getString("weekPlan").trim());
                w.setUserid(rs.getInt("userid"));
                w.setOprtime(ContextUtils.dateToStrLong(rs.getTimestamp("oprtime")));
                w.setRemark(rs.getString("remark").trim());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return w;
    }

    //修改工作周报表
    public void updateWeeklyReport(WeeklyReportVo w) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update WeeklyReport set weekDate='";
            sql += w.getWeekDate() + "',workContent='";
            sql += w.getWorkContent() + "',workReview='";
            sql += w.getWorkReview() + "',question='";
            sql += w.getQuestion() + "',warning='";
            sql += w.getWarning() + "',weekPlan='";
            sql += w.getWeekPlan() + "',userid=";
            sql += w.getUserid() + ",oprtime='";
            sql += w.getOprtime() + "',remark='";
            sql += w.getRemark() + "' where id=" + w.getId();
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查找工作周报表分页
    public List<WeeklyReportVo> findPageWeeklyReport(int startRow, int pageSize) {
        List<WeeklyReportVo> list = new ArrayList<WeeklyReportVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + "  w.*,u.username from WeeklyReport w ";
            sql += "inner join Users u on w.userid=u.userid ";
            sql += " where id not in (";
            sql += "select top " + startRow + " id from WeeklyReport)";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                WeeklyReportVo w = new WeeklyReportVo();
                w.setId(rs.getInt("id"));
                w.setWeekDate(ContextUtils.dateToStrShort(rs.getTimestamp("weekDate")));
                w.setWorkContent(rs.getString("workContent").trim());
                w.setWorkReview(rs.getString("workReview").trim());
                w.setQuestion(rs.getString("question").trim());
                w.setWarning(rs.getString("warning").trim());
                w.setWeekPlan(rs.getString("weekPlan").trim());
                w.setUserid(rs.getInt("userid"));
                w.setOprtime(ContextUtils.dateToStrLong(rs.getTimestamp("oprtime")));
                w.setRemark(rs.getString("remark").trim());
                w.setUsername(rs.getString("username").trim());
                list.add(w);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    //订单表
    //新增订单表
    public void addOrders(OrdersVo o) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "insert into Orders values('";
            sql += o.getOrderId() + "',";
            sql += o.getCustid() + ",";
            sql += o.getUserid() + ",'";
            sql += o.getOrderType() + "','";
            sql += o.getOrderStatus() + "','";
            sql += o.getProcess() + "',";
            sql += o.getTotalMoney() + ",";
            if(o.getOprtime()==null||o.getOprtime().equals("")){
                sql +="getdate(),'";
            }else {
                sql += "'"+o.getOprtime()+",'";
            }
            sql += o.getOperator() + "','";
            sql += o.getRemark() + "' )";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据订单表id查找
    public OrdersVo findOrdersId(String orderId) {
        OrdersVo o = new OrdersVo();
        Connection conn = DBPool.openDB();
        try {
            String sql = "select * from Orders where orderId='" + orderId + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                o.setOrderId(rs.getString("orderId").trim());
                o.setCustid(rs.getInt("custid"));
                o.setUserid(rs.getInt("userid"));
                o.setOrderType(rs.getString("orderType").trim());
                o.setOrderStatus(rs.getString("orderStatus").trim());
                o.setProcess(rs.getString("process").trim());
                o.setTotalMoney(rs.getFloat("totalMoney"));
                o.setOprtime(ContextUtils.dateToStrLong(rs.getTimestamp("oprtime")));
                o.setOperator(rs.getString("operator").trim());
                o.setRemark(rs.getString("remark").trim());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    //修改订单表
    public void updateOrders(String orderStatus, String orderId) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update Orders set orderStatus='";

            sql += orderStatus + "' where orderId='" + orderId + "' ";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询订单表记录数
    public int findCountOrders(String custid) {
        int count = 0;
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from Orders where custid=" + custid;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return count;
    }
    //查询订单表记录数
    public int findCountOrders1(String orderType) {
        int count = 0;
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from Orders where orderType='" + orderType+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return count;
    }

    //查找订单表分页
    public List<OrdersVo> findPageOrders(int startRow, int pageSize, String custid, String orderType) {
        List<OrdersVo> list = new ArrayList<OrdersVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "";
            if (orderType.equals("销售出库")) {
                sql = "select top " + pageSize + " o.*,c.custname,u.username,(u1.username) username1 from Orders o ";
                sql += "inner join CustomerInfo c on c.custId=o.custid ";
                sql += "inner join Users u on u.userid=o.userid ";
                sql += "inner join Users u1 on u1.userid=o.operator ";
                sql += " where orderId not in (";
                sql += "select top " + startRow + " orderId from Orders)";
                sql += " and o.custid=" + custid+" order by orderId desc";
            } else {
                sql = "select top " + pageSize + " o.*,s.supplierName,u.username,(u1.username) username1 from Orders o ";
                sql += "inner join Users u on u.userid=o.userid ";
                sql += "inner join Users u1 on u1.userid=o.operator ";
                sql += "inner join Supplier s on s.supplierId=o.custid ";
                sql += " where orderId not in (";
                sql += "select top " + startRow + " orderId from Orders)";
                sql += " and orderType='" + orderType + "' order by orderId desc";
            }
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                OrdersVo o = new OrdersVo();
                o.setOrderId(rs.getString("orderId").trim());
                o.setCustid(rs.getInt("custid"));
                o.setUserid(rs.getInt("userid"));
                o.setOrderType(rs.getString("orderType").trim());
                o.setOrderStatus(rs.getString("orderStatus"));
                o.setOrderStatus1(rs.getString("orderStatus"));
                o.setProcess(rs.getString("process").trim());
                o.setTotalMoney(rs.getFloat("totalMoney"));
                o.setOprtime(ContextUtils.dateToStrShort(rs.getTimestamp("oprtime")));
                o.setOperator(rs.getString("operator").trim());
                o.setRemark(rs.getString("remark").trim());
                if (custid != null) {
                    o.setCustname(rs.getString("custname").trim());
                } else {
                    o.setCustname(rs.getString("supplierName").trim());
                }

                o.setUsername(rs.getString("username").trim());
                o.setOperatorName(rs.getString("username1"));

                list.add(o);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //订单报表
    public List<OrdersVo> findPageOrdersCount(int startRow, int pageSize,String customerInfoVos,String usersVos,String stime,String etime) {
        List<OrdersVo> list = new ArrayList<OrdersVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "";
            sql = "select top " + pageSize + " o.*,c.custname,u.username,(u1.username) username1 from Orders o ";
            sql += "inner join CustomerInfo c on c.custId=o.custid ";
            sql += "inner join Users u on u.userid=o.userid ";
            sql += "inner join Users u1 on u1.userid=o.operator ";
            sql += " where orderId not in (";
            sql += "select top " + startRow + " orderId from Orders) and  1=1 and orderType='销售出库' ";
            if(customerInfoVos!=null){
                if(!customerInfoVos.equals("0")){
                    sql+=" and o.custid="+customerInfoVos;
                }
                if(!usersVos.equals("0")){
                    sql+=" and o.userid="+usersVos;
                }
                if (stime != null) {
                    if (!stime.equals("")) {
                        sql += " and o.oprtime between '" + stime + " 00:00' and '" + etime + " 23:59' ";
                    }
                }
            }
            sql+="  order by orderId desc ";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                OrdersVo o = new OrdersVo();
                o.setOrderId(rs.getString("orderId").trim());
                o.setCustid(rs.getInt("custid"));
                o.setUserid(rs.getInt("userid"));
                o.setOrderType(rs.getString("orderType").trim());
                o.setOrderStatus(rs.getString("orderStatus"));
                o.setOrderStatus1(rs.getString("orderStatus"));
                o.setProcess(rs.getString("process").trim());
                o.setTotalMoney(rs.getFloat("totalMoney"));
                o.setOprtime(ContextUtils.dateToStrShort(rs.getTimestamp("oprtime")));
                o.setOperator(rs.getString("operator").trim());
                o.setRemark(rs.getString("remark").trim());

                o.setCustname(rs.getString("custname").trim());


                o.setUsername(rs.getString("username").trim());
                o.setOperatorName(rs.getString("username1"));

                list.add(o);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    //根据供应商id查找商品信息
    public List<ProductVo> findSupplierIdProduct(String supplierId) {
        List<ProductVo> list = new ArrayList<ProductVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from Product where supplierId=" + supplierId;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ProductVo p = new ProductVo();
                p.setProdid(rs.getString("prodid"));
                p.setProdname(rs.getString("prodname"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    //根据客户id查找订单
    public List<OrdersVo> findOrderCustid(String custid){
        List<OrdersVo> list=new ArrayList<OrdersVo>();
        Connection conn=DBPool.openDB();
        try{
            Statement stmt=conn.createStatement();
            String sql="select * from Orders where custid="+custid;
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                OrdersVo o=new OrdersVo();
                o.setOrderId(rs.getString("orderId"));
                list.add(o);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //查找订单表
    public List<OrdersVo> findOrders() {
        List<OrdersVo> list = new ArrayList<OrdersVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select  * from Orders";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                OrdersVo o = new OrdersVo();
                o.setOrderId(rs.getString("orderId").trim());
                list.add(o);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //查找订单明细的id
    public String findIdOrderDetail(String orderId) {
        String detailId = null;
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select  DetailId  from OrderDetail where orderId in (select orderId from Orders where orderId='" + orderId + "') ";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                detailId = rs.getInt("DetailId") + "";
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return detailId;
    }

    //--------------------------------------------
    //查询订单里的全部明细
    public List<OrderDetailVo> findOrderDetailVos(String orderId) {
        List<OrderDetailVo> list = new ArrayList<OrderDetailVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from OrderDetail where orderId='" + orderId + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                OrderDetailVo o = new OrderDetailVo();
                o.setDetailId(rs.getInt("DetailId"));
                o.setOrderId(rs.getString("orderId").trim());
                o.setProdid(rs.getString("prodid").trim());
                o.setStatus(rs.getString("status"));
                o.setSaleMoney(rs.getFloat("saleMoney"));
                o.setUnitId(rs.getInt("UnitId"));
                o.setRegPerson(rs.getString("regPerson").trim());
                o.setRegPassword(rs.getString("regPassword").trim());
                o.setServicePeriod(rs.getString("servicePeriod").trim());
                o.setExpireDate(rs.getString("expireDate").trim());
                o.setProdCount(rs.getInt("prodCount"));
                o.setTotalMoney(rs.getFloat("totalMoney"));
                o.setOprtime(ContextUtils.dateToStrLong(rs.getTimestamp("oprtime")));
                o.setOperator(rs.getString("operator").trim());
                o.setRemark(rs.getString("remark").trim());
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //生成订单Id
    public String findOrderId() {
        String orderId = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String orderIds = sdf.format(new Date());
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select (isnull(max(orderId),0)+1) id  from Orders where orderId like '" + orderIds + "%'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                orderId = rs.getString("id");
            }
            if (orderId.equals("1")) {
                orderIds += "000001";
            } else {
                orderIds = rs.getString("id");
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderIds;
    }


    //累加订单的总金额
    public void updateOrdersTotalMoney(String orderId) {
        Connection conn = DBPool.openDB();
        float money = findOrdersTotalMoneys(orderId);
        try {
            Statement stmt = conn.createStatement();
            String sql = "update Orders set totalMoney=" + money + " where orderId='" + orderId + "'";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //查询订单对应订单明细的总金额
    public float findOrdersTotalMoneys(String orderId) {
        Connection conn = DBPool.openDB();
        float money = 0;
        try {
            Statement stmt = conn.createStatement();
            String sql = "select sum(totalMoney) money from OrderDetail where orderId='" + orderId + "'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                money = rs.getFloat("money");
            }
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return money;
    }


    //计算商品的库存
    public void updateProductCounts(String prodid, int pcount) {
        Connection conn = DBPool.openDB();
        //查询商品的原数量
        int count1 = findProductCount(prodid);
        //查询订单明细对应的商品的销售数量
        int sum = (count1 - (pcount));
        try {
            String sql = "update Product set prodCount=";
            sql += sum + " where prodid='" + prodid + "'";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //判断订单是否存在
    public boolean isOrder(String orderId) {
        boolean b = true;
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from Orders where orderId='" + orderId + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                b = false;
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return b;

    }


    //-----------------------------------------------------------------------------------------------------

    //订单明细表
    //新增订单明细表
    public void addOrderDetail(OrderDetailVo o) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "insert into OrderDetail values('";
            sql += o.getOrderId() + "','";
            sql += o.getProdid() + "','";
            sql += o.getStatus() + "',";
            sql += o.getSaleMoney() + ",";
            sql += o.getUnitId() + ",'";
            sql += o.getRegPerson() + "','";
            sql += o.getRegPassword() + "','";
            sql += o.getServicePeriod() + "','";
            sql += o.getExpireDate() + "',";
            sql += o.getProdCount() + ",";
            sql += o.getTotalMoney() + ",";
            sql += "getdate(),'";
            sql += o.getOperator() + "','";
            sql += o.getRemark() + "' )";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据订单表id查找
    public OrderDetailVo findOrderDetailId(String DetailId) {
        OrderDetailVo o = new OrderDetailVo();
        Connection conn = DBPool.openDB();
        try {
            String sql = "select * from OrderDetail where DetailId='" + DetailId + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                o.setDetailId(rs.getInt("DetailId"));
                o.setOrderId(rs.getString("orderId").trim());
                o.setProdid(rs.getString("prodid").trim());
                o.setStatus(rs.getString("status"));
                o.setSaleMoney(rs.getFloat("saleMoney"));
                o.setUnitId(rs.getInt("UnitId"));
                o.setRegPerson(rs.getString("regPerson").trim());
                o.setRegPassword(rs.getString("regPassword").trim());
                o.setServicePeriod(rs.getString("servicePeriod").trim());
                o.setExpireDate(rs.getString("expireDate").trim());
                o.setProdCount(rs.getInt("prodCount"));
                o.setTotalMoney(rs.getFloat("totalMoney"));
                o.setOprtime(ContextUtils.dateToStrLong(rs.getTimestamp("oprtime")));
                o.setOperator(rs.getString("operator").trim());
                o.setRemark(rs.getString("remark").trim());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    //修改订单明细表
    public void updateOrderDetail(OrderDetailVo o) {
        Connection conn = DBPool.openDB();
        try {
            String sql = "update OrderDetail set orderId='";
            sql += o.getOrderId() + "',prodid='";
            sql += o.getProdid() + "',status='";
            sql += o.getStatus() + "',saleMoney=";
            sql += o.getSaleMoney() + ",UnitId=";
            sql += o.getUnitId() + ",regPerson='";
            sql += o.getRegPerson() + "',regPassword='";
            sql += o.getRegPassword() + "',servicePeriod='";
            sql += o.getServicePeriod() + "',expireDate='";
            sql += o.getExpireDate() + "',prodCount=";
            sql += o.getProdCount() + ",totalMoney=";
            sql += o.getTotalMoney() + ",oprtime=";
            sql += "getdate(),operator='";
            sql += o.getOperator() + "',remark='";
            sql += o.getRemark() + "' where DetailId=" + o.getDetailId();
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除订单明细表
    public void depOrderDetail(String DetailId) {
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete from OrderDetail where DetailId=" + DetailId;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询订单表记录数
    public int findCountOrderDetail(String orderId) {
        int count = 0;
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(*) cnt from OrderDetail where orderId='" + orderId + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return count;
    }

    //查找订单明细表分页
    public List<OrderDetailVo> findPageOrderDetail(int startRow, int pageSize, String orderId) {
        List<OrderDetailVo> list = new ArrayList<OrderDetailVo>();
        Connection conn = DBPool.openDB();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select top " + pageSize + " o.*,u.unitName,p.prodname  from OrderDetail o ";
            sql += "inner join Product p on p.prodid=o.prodid ";
            sql += "inner join Unit u on u.unitId=o.unitId ";
            sql += " where DetailId not in (";
            sql += "select top " + startRow + " DetailId from OrderDetail) ";
            sql += " and orderId='" + orderId + "' ";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                OrderDetailVo o = new OrderDetailVo();
                o.setDetailId(rs.getInt("DetailId"));
                o.setOrderId(rs.getString("orderId").trim());
                o.setProdid(rs.getString("prodid").trim());
                o.setStatus(rs.getString("status"));
                o.setSaleMoney(rs.getFloat("saleMoney"));
                o.setUnitId(rs.getInt("UnitId"));
                o.setRegPerson(rs.getString("regPerson").trim());
                o.setRegPassword(rs.getString("regPassword").trim());
                o.setServicePeriod(rs.getString("servicePeriod").trim());
                o.setExpireDate(rs.getString("expireDate").trim());
                o.setProdCount(rs.getInt("prodCount"));
                o.setTotalMoney(rs.getFloat("totalMoney"));
                o.setOprtime(ContextUtils.dateToStrLong(rs.getTimestamp("oprtime")));
                o.setOperator(rs.getString("operator").trim());
                o.setRemark(rs.getString("remark").trim());
                o.setUnitName(rs.getString("unitName").trim());
                o.setProdname(rs.getString("prodname").trim());
                list.add(o);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    public static void main(String[] args) {
        System.out.println(new BaseDAO().findOrderId());


    }
}
