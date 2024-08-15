CREATE TABLE Reimbursement (
  reimbursement_id BIGINT  PRIMARY KEY COMMENT '报销记录ID',
  amount DECIMAL(10, 2) COMMENT '报销金额',
  status VARCHAR(20) COMMENT '报销状态',
  type VARCHAR(20) COMMENT '报销类型',
  applicant_id BIGINT  COMMENT '申请人ID',
  approver_id BIGINT  COMMENT '审核人ID',
  note VARCHAR(255) COMMENT '备注'
) engine=InnoDB charset=utf8mb4 COMMENT='报销表';

CREATE TABLE ReimbursementType (
   type_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '报销类型ID',
   name VARCHAR(50) COMMENT '报销类型名称'
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='报销类型表';