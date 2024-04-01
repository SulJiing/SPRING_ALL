<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src='${pageContext.request.contextPath }/resources/lib/fullcalendar-6.1.10/dist/index.global.min.js'></script>
<script>
  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
      initialView: 'dayGridMonth'
      , initialDate:'1975-11-21'
      , events: '${pageContext.request.contextPath }/fullcalendar/case1/json'
    });
    calendar.render();
  });
</script>
<pre>
 	설치 : https://fullcalendar.io/docs/initialize-globals
 	event 객체 구조 : https://fullcalendar.io/docs/event-object
 	event 소스 종류 : https://fullcalendar.io/docs/event-source
 	event from json : https://fullcalendar.io/docs/events-json-feed
</pre>
<div id='calendar' class="w-50"></div>
