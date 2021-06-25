package com.dicoding.capstoneakhir.core.di

import androidx.room.Room.databaseBuilder as Room
import com.dicoding.capstoneakhir.core.Constant.BASE_URL
import com.dicoding.capstoneakhir.core.Constant.DATABASE
import com.dicoding.capstoneakhir.core.Constant.TIME_OUT
import com.dicoding.capstoneakhir.core.domain.repository.IMoveeRepository
import com.dicoding.capstoneakhir.core.source.MoveeRepository
import com.dicoding.capstoneakhir.core.source.local.LocalDataSource
import com.dicoding.capstoneakhir.core.source.local.room.MoveeDatabase
import com.dicoding.capstoneakhir.core.source.remote.RemoteDataSource
import com.dicoding.capstoneakhir.core.source.remote.network.TMDBService
import com.dicoding.capstoneakhir.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase.getBytes
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner.Builder as CertificatePinner
import okhttp3.OkHttpClient.Builder as OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory.create as GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS

val databaseModule = module {
    factory {
        get<MoveeDatabase>().moveeDAO()
    }
    single {
        val sqliteDatabase: ByteArray = getBytes("dicoding".toCharArray())
        val supportFactory = SupportFactory(sqliteDatabase)

        Room(androidContext(), MoveeDatabase::class.java, DATABASE)
            .fallbackToDestructiveMigration()
            .openHelperFactory(supportFactory)
            .build()
    }
}

val networkModule = module {
    val hostName = "*.themoviedb.org"
    val certificatePinner = CertificatePinner()
        .add(hostName, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
        .add(hostName, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
        .add(hostName, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
        .add(hostName, "sha256/KwccWaCgrnaw6tsrrSO61FgLacNgG2MMLq8GE6+oP5I=")
        .build()

    single {
        OkHttpClient()
            .addInterceptor(HttpLoggingInterceptor().setLevel(Level.BODY))
            .connectTimeout(TIME_OUT.toLong(), SECONDS)
            .readTimeout(TIME_OUT.toLong(), SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory())
            .client(get())
            .build()

        retrofit.create(TMDBService::class.java)
    }
}

val repositoryModule = module {
    single {
        LocalDataSource(get())
    }

    single {
        RemoteDataSource(get())
    }

    factory {
        AppExecutors()
    }

    single<IMoveeRepository> {
        MoveeRepository(
            get(),
            get(),
            get()
        )
    }
}