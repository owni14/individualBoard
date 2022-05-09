<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="frmPaging" action="/mypro17/myboard/list" method="get">
<input type="hidden" name="b_no" value="${param.b_no}">
<input type="hidden" name="page" value="${param.page}">
<input type="hidden" name="searchType" value="${param.searchType}">
<input type="hidden" name="keyword" value="${param.keyword}">
<input type="hidden" name="showRow" value="${param.showRow}">
</form>