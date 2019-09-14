package org.yun.ssmAndSecurity.mapper.prodiver;

/**
 * @ClassName UserProvider
 * @Description TODO
 * @Autor 拾笔丶
 * @Date 2019/9/7 22:40
 */
public class UserProvider {

    public String findAll(){
        StringBuffer sql = new StringBuffer("SELECT * from user ");
        return sql.toString();
    }




    //例子
//    public String listPage(Map<String,Object> params){
//        BulletinDTO dto = (BulletinDTO) params.get("dto");
//        StringBuffer sql = new StringBuffer( "SELECT * FROM bulletin WHERE  is_delete = 0 ");
//        if(StringUtil.isNotEmpty(dto.getName())){
//            sql.append(" AND  name  like '%" + dto.getName() + "%' " );
//        }
//        if(StringUtil.isNotEmpty(dto.getContent())){
//            sql.append( " AND content like '%" + dto.getContent() + "%' " );
//        }
//        sql.append(" ORDER BY liked , shared ASC , topping , updated_time DESC ");
//        return sql.toString();
//    }





//    public String listPage(Map<String, Object> params) {
//        TopNavigationDTO dto = (TopNavigationDTO) params.get("dto");
////        return new SQL() {{
////            SELECT("*");
////            FROM("top_navigation");
////            if (StringUtil.isNotEmpty(dto.getName())){
////                if (dto.getIsDelete() == false){//删除字段为0
////                WHERE( "name  like '%" + dto.getName() +"'");
////                }
////            }
////
////            ORDER_BY("sort","created_time DESC");
////        }}.toString();
//        StringBuffer sql = new StringBuffer("SELECT * FROM top_navigation WHERE is_delete =     0 ");
//        if (StringUtil.isNotEmpty(dto.getName())) {
//            sql.append(" AND  name  like '%" + dto.getName() + "%' " );
//        }
//        if(dto.getShowType() != null){
//            sql.append(" AND show_type = " + dto.getShowType());
//        }
//        sql.append(" ORDER BY sort ASC ,created_time DESC ");
//        return sql.toString();
//    }




//    public String listPage(Map<String,Object> params){
//        FactoryProductTypeDTO fptDto = (FactoryProductTypeDTO) params.get("fptDto");
//        StringBuffer sql = new StringBuffer( "SELECT * FROM factory_product_type WHERE  is_delete = 0 ");
//        if(StringUtil.isNotEmpty(fptDto.getFptName())){
//            sql.append(" AND  fpt_name  like '%" + fptDto.getFptName() + "%' " );
//        }
//        if(StringUtil.isNotEmpty(fptDto.getFpName())){
//            sql.append(" OR  fp_name  like '%" + fptDto.getFpName() + "%' " );
//        }
//        sql.append(" ORDER BY updated_time DESC ");
//        return sql.toString();
//    }



    /*
    * SELECT f.`factory_name`,fpt.`fpt_name`,fpt.`fp_figure`,fpt.`fp_name`
    FROM `factory_product_type` fpt, `factory` f
    WHERE fpt.`f_id` = f.id
    AND fpt.is_delete = 0
    ORDER BY fpt.updated_time DESC;
    *
    * */
//    public String list(Map<String,Object> params){
//        FactoryDtoAndFptDTO fafptDto = (FactoryDtoAndFptDTO) params.get("fafptDto");
//        StringBuffer sql = new StringBuffer( "SELECT f.`factory_name`,fpt.`fpt_name`,fpt.`fp_figure`,fpt.`fp_name`" +
//                " FROM `factory_product_type` fpt, `factory` f " +
//                " WHERE fpt.`f_id` = f.id AND fpt.is_delete = 0 AND f.is_delete = 0 ");
//        if(StringUtil.isNotEmpty(fafptDto.getFactoryName())){
//            sql.append(" AND  factory_name  like '%" + fafptDto.getFactoryName() + "%' " );
//        }
////        if(StringUtil.isNotEmpty(fafptDto.getFpName())){
////            sql.append(" OR  fp_name  like '%" + fafptDto.getFpName() + "%' " );
////        }
////        sql.append(" ORDER BY updated_time DESC ");
//        return sql.toString();
//    }



//    public String listPage(Map<String,Object> params){
//        FactoryDTO dto = (FactoryDTO) params.get("dto");
//        StringBuffer sql = new StringBuffer( "SELECT factory_name  FROM factory WHERE  is_delete = 0 ");
//        if(StringUtil.isNotEmpty(dto.getFactoryName())){
//            sql.append(" AND  factory_name  like '%" + dto.getFactoryName() + "%' " );
//        }
//        sql.append(" ORDER BY updated_time DESC ");
//        return sql.toString();
//    }
}
