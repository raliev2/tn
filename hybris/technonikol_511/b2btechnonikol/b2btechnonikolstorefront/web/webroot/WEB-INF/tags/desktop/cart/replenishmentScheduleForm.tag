<%@ tag body-content="empty" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="formUtil" tagdir="/WEB-INF/tags/desktop/form"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>

<spring:theme code="replenishmentScheduleForm.activateDaily" var="Daily"/>
<spring:theme code="replenishmentScheduleForm.activateWeekly" var="Weekly"/>
<spring:theme code="replenishmentScheduleForm.activateMonthly" var="Monthly"/>

<%--<c:url value="/checkout/multi/schedule-order" var="schedule_order_url" />  --%>
       
<script>
    $(document).ready(function() {
    	$(function() {
    		$( "#replenishmentStartDate" ).datepicker();
    	});
    	
    <%--    $('#placeReplenishmentOrderButton').click(function() {
        		var data = $('#replenishmentForm').serialize();
            $.ajax({
                data : data,
                type : 'post',
                url : '${schedule_order_url}',
                success : function(data) {
                	$(data).modal();
                }
            });
        }); --%>
    });
</script>

 <div class="checkout__replenishment-div" id="replenishment-schedule-div" style="display:none;">
	<div>
		<div>
			<h4>
				Установите параметры повтора заказа
			</h4>
		</div>

		<div>
			<div>
				<div><formUtil:formInputBox idKey="replenishmentStartDate" labelKey="Дата начала" path="replenishmentStartDate" inputCSS="date" mandatory="true" /></div>
			</div>

			<div style="padding-top:20px;">
				<div class="replenishmentFrequency"><form:radiobutton path="replenishmentRecurrence" id="replenishmentFrequencyD" label="Ежедневно" value="DAILY" /></div>

				<label for="nDays">
					Каждый
				</label>
				<form:select id="nDays" path="nDays" >
					<form:options items="${days}" />
				</form:select>				
				день
			</div>

			<div style="padding-top:20px;">
				<div class="replenishmentFrequency"><form:radiobutton path="replenishmentRecurrence" id="replenishmentFrequencyW" label="Еженедельно" value="WEEKLY" /></div>

				<table style="width:370px">
					<tr>
						<td>
							<label for="daysOfWeek">Отправлять каждый</label>
							<form:select id="daysOfWeek" path="nDaysOfWeek"  multiple="true">
								<form:options items="${daysOfWeek}" itemLabel="name" itemValue="code" />
							</form:select>
						</td>
		
						<td>
							<label for="nWeeks">Каждую</label>
							<form:select id="nWeeks" path="nWeeks" >
								<form:options items="${weeks}" />
							</form:select>
							неделю
						</td>
					</tr>
				</table>
				
			</div>

			<div style="padding-top:20px;">
				<div class="replenishmentFrequency"><form:radiobutton path="replenishmentRecurrence" id="replenishmentFrequencyM" label="Ежемесячно" value="MONTHLY" /></div>

				<label for="nthDayOfMonth">
					Каждый
				</label>
				<form:select id="nthDayOfMonth" path="nthDayOfMonth" >
					<form:options items="${days}" />
				</form:select>
				день месяца
			</div>

		</div>
	</div>
	
	
	<div style="padding-top:20px;">
		<button type="submit" class="g-button-black" id="placeReplenishmentOrderButton">
				Отправить
		</button>
	</div>
 
</div>
