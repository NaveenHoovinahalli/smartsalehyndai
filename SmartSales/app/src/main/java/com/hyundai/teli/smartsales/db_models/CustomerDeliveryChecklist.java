package com.hyundai.teli.smartsales.db_models;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

/**
 * Created by naveen on 3/3/15.
 */
public class CustomerDeliveryChecklist extends SugarRecord<CustomerDeliveryChecklist> {

    @SerializedName("dealer")
    String dealer;

    @SerializedName("customer_name")
    String customerName;

    @SerializedName("vin_number")
    String vinNumber;

    @SerializedName("model")
    String model;


    @SerializedName("delivery_date")
    String deliveryDate;

    @SerializedName("instrument_panel")
    String instrumentPanel;

    @SerializedName("HVAC_control")
    String hvacControl;

    @SerializedName("airflow")
    String airflow;
    String mobileNumber;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    String seatAdjustment;

    public String getSeatAdjustment() {
        return seatAdjustment;
    }

    public void setSeatAdjustment(String seatAdjustment) {
        this.seatAdjustment = seatAdjustment;
    }

    @SerializedName("MID")
    String mid;

    @SerializedName("headlight turn signals fog light")
    String headLightFogLight;

    @SerializedName("gear_shift_indicator")
    String gearShiftIndicator;

    @SerializedName("IRVM_cabin_light")
    String irvmCabinLight;

    @SerializedName("vanity_mirror_sun_visar")
    String  vanityMirrorSunVisar;

    @SerializedName("glove_box_cooling_slot")
    String gloveBoxColling;

    @SerializedName("tool_kit")
    String toolKit;

    @SerializedName("jack")
    String jack;

    @SerializedName("first_aid_kit")
    String firstAidKit;

    @SerializedName("warning_triangle")
    String warningTriangle;

    @SerializedName("insurance_papers")
    String insurancePapers;

    @SerializedName("registration_documents")
    String registrationDocuments;

    @SerializedName("roadside_assistance_details")
    String roadsideAssistance;

    @SerializedName("sales_invoice")
    String salesInvoice;

    @SerializedName("payments_receipts")
    String paymentsRecits;

    @SerializedName("fiance_documents")
    String financeDocuments;

    @SerializedName("extended_warranty_policy")
    String  extandedWarrantyPolicy;

    @SerializedName("customer_signature")
    String customerSignature;

    @SerializedName("date")
    String date;

    @SerializedName("sales_consultant")
    String salesConsultnt;

    @SerializedName("seat_belts")
    String seatBelts;

    @SerializedName("door_locks_child_lock")
    String doorLockChildLock;

    @SerializedName("hazard_warning")
    String hazardWarning;

    @SerializedName("bluetooth_and_steering_mounted_controls")
    String blueetothandstreeing;

    @SerializedName("headlight_levelers")
    String headlightLevelers;

    @SerializedName("gear_shifting_hand_break_lever")
    String gearShiftHandBreakLeveler;

    @SerializedName("ORVMs")
    String orvms;

    @SerializedName("power_socket")
    String powerSocket;

    @SerializedName("tilt_and_telescopic_steering")
    String tiltAndTelescopic;

    @SerializedName("jack_mounting_points")
    String jackMountingPoints;

    @SerializedName("rear_defogger")
    String rearDefoger;

    @SerializedName("jack_and_tool_kit_location")
    String jackTollKitLocation;

    @SerializedName("towing_hook")
    String towingHook;

    @SerializedName("wiper_functions")
    String wiperFunctions;

    @SerializedName("break_oil_engine_oil")
    String breakOilEngileOil;

    @SerializedName("ignition_keys_central_locking")
    String ignationKeysCentralLocking;

    @SerializedName("music_system_controls")
    String musicSystemControls;

    @SerializedName("owners_manual_cum_free_service_coupons")
    String ownersManualCumFreeServiceCoupuns;



    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getInstrumentPanel() {
        return instrumentPanel;
    }

    public void setInstrumentPanel(String instrumentPanel) {
        this.instrumentPanel = instrumentPanel;
    }

    public String getHvacControl() {
        return hvacControl;
    }

    public void setHvacControl(String hvacControl) {
        this.hvacControl = hvacControl;
    }

    public String getAirflow() {
        return airflow;
    }

    public void setAirflow(String airflow) {
        this.airflow = airflow;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getHeadLightFogLight() {
        return headLightFogLight;
    }

    public void setHeadLightFogLight(String headLightFogLight) {
        this.headLightFogLight = headLightFogLight;
    }

    public String getGearShiftIndicator() {
        return gearShiftIndicator;
    }

    public void setGearShiftIndicator(String gearShiftIndicator) {
        this.gearShiftIndicator = gearShiftIndicator;
    }

    public String getIrvmCabinLight() {
        return irvmCabinLight;
    }

    public void setIrvmCabinLight(String irvmCabinLight) {
        this.irvmCabinLight = irvmCabinLight;
    }

    public String getVanityMirrorSunVisar() {
        return vanityMirrorSunVisar;
    }

    public void setVanityMirrorSunVisar(String vanityMirrorSunVisar) {
        this.vanityMirrorSunVisar = vanityMirrorSunVisar;
    }

    public String getGloveBoxColling() {
        return gloveBoxColling;
    }

    public void setGloveBoxColling(String gloveBoxColling) {
        this.gloveBoxColling = gloveBoxColling;
    }

    public String getToolKit() {
        return toolKit;
    }

    public void setToolKit(String toolKit) {
        this.toolKit = toolKit;
    }

    public String getJack() {
        return jack;
    }

    public void setJack(String jack) {
        this.jack = jack;
    }

    public String getFirstAidKit() {
        return firstAidKit;
    }

    public void setFirstAidKit(String firstAidKit) {
        this.firstAidKit = firstAidKit;
    }

    public String getWarningTriangle() {
        return warningTriangle;
    }

    public void setWarningTriangle(String warningTriangle) {
        this.warningTriangle = warningTriangle;
    }

    public String getInsurancePapers() {
        return insurancePapers;
    }

    public void setInsurancePapers(String insurancePapers) {
        this.insurancePapers = insurancePapers;
    }

    public String getRegistrationDocuments() {
        return registrationDocuments;
    }

    public void setRegistrationDocuments(String registrationDocuments) {
        this.registrationDocuments = registrationDocuments;
    }

    public String getRoadsideAssistance() {
        return roadsideAssistance;
    }

    public void setRoadsideAssistance(String roadsideAssistance) {
        this.roadsideAssistance = roadsideAssistance;
    }

    public String getSalesInvoice() {
        return salesInvoice;
    }

    public void setSalesInvoice(String salesInvoice) {
        this.salesInvoice = salesInvoice;
    }

    public String getPaymentsRecits() {
        return paymentsRecits;
    }

    public void setPaymentsRecits(String paymentsRecits) {
        this.paymentsRecits = paymentsRecits;
    }

    public String getFinanceDocuments() {
        return financeDocuments;
    }

    public void setFinanceDocuments(String financeDocuments) {
        this.financeDocuments = financeDocuments;
    }

    public String getExtandedWarrantyPolicy() {
        return extandedWarrantyPolicy;
    }

    public void setExtandedWarrantyPolicy(String extandedWarrantyPolicy) {
        this.extandedWarrantyPolicy = extandedWarrantyPolicy;
    }

    public String getCustomerSignature() {
        return customerSignature;
    }

    public void setCustomerSignature(String customerSignature) {
        this.customerSignature = customerSignature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSalesConsultnt() {
        return salesConsultnt;
    }

    public void setSalesConsultnt(String salesConsultnt) {
        this.salesConsultnt = salesConsultnt;
    }

    public String getSeatBelts() {
        return seatBelts;
    }

    public void setSeatBelts(String seatBelts) {
        this.seatBelts = seatBelts;
    }

    public String getDoorLockChildLock() {
        return doorLockChildLock;
    }

    public void setDoorLockChildLock(String doorLockChildLock) {
        this.doorLockChildLock = doorLockChildLock;
    }

    public String getHazardWarning() {
        return hazardWarning;
    }

    public void setHazardWarning(String hazardWarning) {
        this.hazardWarning = hazardWarning;
    }

    public String getBlueetothandstreeing() {
        return blueetothandstreeing;
    }

    public void setBlueetothandstreeing(String blueetothandstreeing) {
        this.blueetothandstreeing = blueetothandstreeing;
    }

    public String getHeadlightLevelers() {
        return headlightLevelers;
    }

    public void setHeadlightLevelers(String headlightLevelers) {
        this.headlightLevelers = headlightLevelers;
    }

    public String getGearShiftHandBreakLeveler() {
        return gearShiftHandBreakLeveler;
    }

    public void setGearShiftHandBreakLeveler(String gearShiftHandBreakLeveler) {
        this.gearShiftHandBreakLeveler = gearShiftHandBreakLeveler;
    }

    public String getOrvms() {
        return orvms;
    }

    public void setOrvms(String orvms) {
        this.orvms = orvms;
    }

    public String getPowerSocket() {
        return powerSocket;
    }

    public void setPowerSocket(String powerSocket) {
        this.powerSocket = powerSocket;
    }

    public String getTiltAndTelescopic() {
        return tiltAndTelescopic;
    }

    public void setTiltAndTelescopic(String tiltAndTelescopic) {
        this.tiltAndTelescopic = tiltAndTelescopic;
    }

    public String getJackMountingPoints() {
        return jackMountingPoints;
    }

    public void setJackMountingPoints(String jackMountingPoints) {
        this.jackMountingPoints = jackMountingPoints;
    }

    public String getRearDefoger() {
        return rearDefoger;
    }

    public void setRearDefoger(String rearDefoger) {
        this.rearDefoger = rearDefoger;
    }

    public String getJackTollKitLocation() {
        return jackTollKitLocation;
    }

    public void setJackTollKitLocation(String jackTollKitLocation) {
        this.jackTollKitLocation = jackTollKitLocation;
    }

    public String getTowingHook() {
        return towingHook;
    }

    public void setTowingHook(String towingHook) {
        this.towingHook = towingHook;
    }

    public String getWiperFunctions() {
        return wiperFunctions;
    }

    public void setWiperFunctions(String wiperFunctions) {
        this.wiperFunctions = wiperFunctions;
    }

    public String getBreakOilEngileOil() {
        return breakOilEngileOil;
    }

    public void setBreakOilEngileOil(String breakOilEngileOil) {
        this.breakOilEngileOil = breakOilEngileOil;
    }

    public String getIgnationKeysCentralLocking() {
        return ignationKeysCentralLocking;
    }

    public void setIgnationKeysCentralLocking(String ignationKeysCentralLocking) {
        this.ignationKeysCentralLocking = ignationKeysCentralLocking;
    }

    public String getMusicSystemControls() {
        return musicSystemControls;
    }

    public void setMusicSystemControls(String musicSystemControls) {
        this.musicSystemControls = musicSystemControls;
    }

    public String getOwnersManualCumFreeServiceCoupuns() {
        return ownersManualCumFreeServiceCoupuns;
    }

    public void setOwnersManualCumFreeServiceCoupuns(String ownersManualCumFreeServiceCoupuns) {
        this.ownersManualCumFreeServiceCoupuns = ownersManualCumFreeServiceCoupuns;
    }




}
