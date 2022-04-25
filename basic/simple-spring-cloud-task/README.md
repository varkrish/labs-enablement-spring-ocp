# Intro

Deploy a spring boot cloud task app to OpenShift 4 for a simple short-lived apps. The goal if this app is to migrate data from `T_TRANSACTION` into `T_TRX_HISTORY` table, so this app's lifecycle would be `app started`, `migrate data` and `app shutting down`. 