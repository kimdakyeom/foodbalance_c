package com.dkkim.anew.Util

import com.dkkim.anew.Model.ResultFoodList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("getFoodNtrItdntList1") // getFoodNtrItdntList1 리소스 조회 후 자세한 정보 가져오기

    fun getFoodNutriInfo(
        // 파라미터를 url 뒤에 붙여서 전달
        @Query("ServiceKey") serviceKey: String, // 공공데이터포털 인증키
        @Query("desc_kor") foodName: String?, // 식품이름
        @Query("pageNo") pageNo: Int?, // 페이지 번호
        @Query("numOfRows") numOfRows: Int?, // 한 페이지 결과수
        @Query("bgn_year") bgnYear: Int?, // 구축년도
        @Query("animal_plant") animalPlant: String?, // 가공업체
        @Query("type") type: String, // 데이터 포맷(xml/json)
    ): Call<ResultFoodList>

}