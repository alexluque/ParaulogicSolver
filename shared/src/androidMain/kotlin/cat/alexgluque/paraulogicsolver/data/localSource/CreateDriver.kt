package cat.alexgluque.paraulogicsolver.data.localSource

import android.content.Context
import cat.alexgluque.paraulogicsolver.ParaulogicDB
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

lateinit var appContext: Context

actual fun createDriver(): SqlDriver = AndroidSqliteDriver(
    ParaulogicDB.Schema,
    appContext,
    "paraulogicsolver.db"
)