package com.alinesno.infra.base.starter.api.dto;

import com.alinesno.infra.base.starter.entity.GenTable;
import com.alinesno.infra.base.starter.entity.GenTableColumn;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class GenTableDto {
 
    private String tableSchema;
 
    private Long tableId;
 
    private String tableName;
 
    private String tableComment;
 
    private String subTableName;
 
    private String subTableFkName;
 
    private String className;
 
    private String tplCategory;
 
    private String packageName;
 
    private String moduleName;
 
    private String businessName;
 
    private String pagePath;
 
    private String functionName;
 
    private String functionAuthor;
 
    private String genType;
 
    private String genPath;
 
    private GenTableColumn pkColumn;
 
    private GenTable subTable;
 
    private List<GenTableColumn> columns;
 
    private List<Long> menuIds;
 
    private String options;
 
    private String treeCode;
 
    private String treeParentCode;
 
    private String treeName;
 
    private String parentMenuId;
 
    private String parentMenuName;
 
    private String author;
 
    private List<String> schemaList;
  
    private Map<String, Object> params = new HashMap<>();
}
