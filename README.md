# How to Insert Koin

    build.gradle app module
     dependencies {

         // other dependencies...

         // Koin for Kotlin
         //implementation "org.koin:koin-core:$koin_version"

         // Koin for Android
         implementation "org.koin:koin-android:2.0.1"

         // or Koin for Lifecycle scoping
         implementation "org.koin:koin-androidx-scope:2.0.1"

         // or Koin for Android Architecture ViewModel
         implementation "org.koin:koin-androidx-viewmodel:2.0.1"

    }



    single, factory, viewModel and scoped module creation

    class App : Application() {
        override fun onCreate() {
            super.onCreate()

            val koinModule: Module = module {
                single { LayoutInflater.from(get()) }
                single { PersonAdapter(get()) } // or PersonAdapter(androidContext()) 
                single { RestaurantAdapter(get()) }
            }

            // start Koin!
            startKoin {

                androidLogger(Level.DEBUG)

                // declare used Android context
                androidContext(this@App)

                // declare modules
                modules(koinModule)
            }

        }    

    }

    class MainActivity : AppCompatActivity() {

    // create
    private val theInjectedPersonAdapter: PersonAdapter by inject()
    
        // use
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
                 
            recycler.adapter = theInjectedPersonAdapter
                
            // or val theInjectedPersonAdapter : RestaurantAdapter = get()
              
        }
    
    }














