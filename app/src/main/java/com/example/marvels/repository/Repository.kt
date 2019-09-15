package com.example.marvels.repository

import androidx.lifecycle.MutableLiveData
import com.example.marvels.model.DetailsItem
import com.example.marvels.model.Marvel
import com.example.marvels.network.retrofit.RetrofitObject
import com.example.marvels.network.retrofit.ServiceProvider
import com.example.marvels.network.service.CharactersService
import com.example.marvels.network.service.DetailsService
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Repository {

    companion object {
        private var charactersLiveData: MutableLiveData<Marvel>? = MutableLiveData()
        private var detailsItemLiveData: MutableLiveData<Marvel>? = MutableLiveData()

        // if there is data base or from the service this fun should decided
        fun getCharacters(limit: Int, offset: Int): MutableLiveData<Marvel>? {
            callCharactersService(limit, offset)
            return charactersLiveData
        }

        fun getDetails(characterId: Int, serviceName: String): MutableLiveData<Marvel>? {
            callService(characterId, serviceName)
            return detailsItemLiveData
        }

        fun callService(characterId: Int, serviceName: String) {
            var detailsService: DetailsService? = ServiceProvider.createService(DetailsService::class.java)

            var details: Observable<Marvel>? =
                detailsService?.getDetails(
                    characterId = characterId,
                    serviceName = serviceName,
                    ts = RetrofitObject.getTimeStamp(),
                    appId = RetrofitObject.getAPIKey(),
                    hash = RetrofitObject.getHashed()
                )
            if (details != null) {
                details.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<Marvel> {
                        override fun onComplete() {
                        }

                        override fun onSubscribe(d: Disposable) {
                        }

                        override fun onNext(marvel: Marvel) {
                            detailsItemLiveData?.postValue(marvel)
                        }

                        override fun onError(e: Throwable) {
                        }

                    })
            }
        }

        fun callCharactersService(limit: Int, offset: Int) {
            var charactersService: CharactersService? = ServiceProvider.createService(CharactersService::class.java)

            var characters: Observable<Marvel>? =
                charactersService?.getCharacters(
                    limit,
                    offset,
                    RetrofitObject.getTimeStamp(),
                    RetrofitObject.getAPIKey(),
                    RetrofitObject.getHashed()
                )

            if (characters != null) {
                characters.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<Marvel> {
                        override fun onComplete() {
                        }

                        override fun onSubscribe(d: Disposable) {
                        }

                        override fun onError(e: Throwable) {
                        }

                        override fun onNext(marvel: Marvel) {
                            charactersLiveData!!.value = marvel
                        }

                    })
            }
        }
    }


}