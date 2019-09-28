package com.kakaopay.hkp.lgs.base.controller;

/**
 *

 FinancialSupportFileController (/api/financial-supports/file)
 - 데이터 파일에서 각 레코드를 데이터베이스에 저장하는 API 개발
 POST :: "/load-support-data"

 FinancialSupportController (/api/financial-supports)
 - 지원하는 지자체 목록 검색 API 개발
 GET :: ""

 - 지원하는 지자체명을 입력 받아 해당 지자체의 지원정보를 출력하는 API 개발
 GET :: "/by/region"

 - 지원하는 지자체 정보 수정 기능
 PUT :: /by/region


 FinancialSupportSearchController (/api/financial-supports/search)
 - 지원한도 컬럼에서 지원금액으로 내림차순 정렬하여 특정개수만 출력
 GET :: "/regions/higher-limit"

 - 이차보전 컬럼에서 보전 비율이 가장 작은 추천 기관명을 출력
 GET :: "/institutes/lowest-rate"

 -(선책) 특정 기사를 분석하여 본 사용자는 어떤 지자체에서 금융지원을 받는게 가장 좋을지 지자체명을 추천
 GET :: "/regions/most-suitable"

 (선택) AccountController
 - signup
 - signin
 - refresh

 */
public abstract class BaseApiController {

}