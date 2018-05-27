import os

from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import scoped_session, sessionmaker

# db settings
db_user = os.environ['DB_USER']
db_pass = os.environ['DB_PASS']
db_host = os.environ['DB_HOST']
db_name = 'crypto_indexing'
engine = create_engine("mysql://%s:%s@%s/%s?charset=utf8&use_unicode=0" % (db_user, db_pass, db_host, db_name),
                       echo=False,
                       pool_recycle=1800)

db = scoped_session(sessionmaker(autocommit=False,
                                 autoflush=False,
                                 bind=engine))
Base = declarative_base()
