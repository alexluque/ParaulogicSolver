package cat.alexgluque.paraulogicsolver.data.localSource

import cat.alexgluque.paraulogicsolver.ParaulogicDB
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual fun createDriver(): SqlDriver = NativeSqliteDriver(
    ParaulogicDB.Schema,
    "paraulogicsolver.db"
)