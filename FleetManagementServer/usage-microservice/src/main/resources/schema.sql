CREATE TABLE IF NOT EXISTS usage_statistic (
  id                    BIGINT AUTO_INCREMENT,
  vehicle_id            BIGINT,
  pid                   BIGINT,
  data                  BIGINT,
  PRIMARY KEY (id)
);
