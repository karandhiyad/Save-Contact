package com.karan.savecontact.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;


@Dao
public interface DatabaseInterface {

    //TBLCategory
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTBLCategory(TBLCategory tblCategory);

    @Query("select * from TBLCategory")
    List<TBLCategory> getTBLCategory();

    @Query("select * from TBLCategory")
    LiveData<List<TBLCategory>> getLiveTBLCategory();

    @Query("update TBLCategory set category = :NewCategory where category = :Category")
    void updateTBLCategory (String Category,String NewCategory);

    @Query("select * from TBLCategory where category = :Category")
    List<TBLCategory> getTBLCategoryByCategory(String Category);

    @Query("DELETE FROM TBLCategory where category = :Category")
    void deleteTBLCategory(String Category);


    //TBLContact
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTBLContact(TBLContact tblContact);

    @Query("select * from TBLContact")
    List<TBLContact> getTBLContact();

    @Query("select * from TBLContact")
    LiveData<List<TBLContact>> getLiveTBLContact();

    @Query("update TBLContact set firstname = :FirstName, lastname = :LastName, mobileNumber = :NewMobileNumber , email = :Email , category = :Category where mobilenumber = :MobileNumber")
    void updateTBLContact (String FirstName, String LastName, String NewMobileNumber, String Email,String Category, String MobileNumber);

    @Query("select * from TBLContact where category = :Category")
    List<TBLContact> getTBLContactCategory(String Category);

    @Query("select * from TBLContact where mobilenumber = :MobileNumber")
    List<TBLContact> getTBLContactByMobileNumber(String MobileNumber);

    @Query("DELETE FROM TBLContact where mobilenumber = :MobileNumber")
    void deleteTBLContact(String MobileNumber);

//    //TBLUnitVerifyUserDetail
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void addTBLUnitVerifyUserDetail (TBLUnitVerifyUserDetail tblUnitVerifyUserDetail);
//
//    @Query("select * from TBLUnitVerifyUserDetail")
//    List<TBLUnitVerifyUserDetail> getTBLUnitVerifyUserDetail();
//
//    @Query("select * from TBLUnitVerifyUserDetail where user_id = :user_id")
//    List<TBLUnitVerifyUserDetail> getTBLUnitVerifyUserDetailByUserId (String user_id);
//
//    @Query("DELETE FROM TBLUnitVerifyUserDetail")
//    void DeleteTBLUnitVerifyUserDetail();
//
//
//    //TBLLoginUserDetail
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void addTBLLoginUserDetail (TBLLoginUserDetail tblLoginUserDetail);
//
//    @Query("select * from TBLLoginUserDetail")
//    List<TBLLoginUserDetail> getTBLLoginUserDetail();
//
//    @Query("select * from TBLLoginUserDetail where user_id = :user_id")
//    List<TBLLoginUserDetail> getTBLLoginUserDetailByUserId (int user_id);
//
//    @Query("update TBLLoginUserDetail set authorization = :authorization where user_id = :user_id")
//    void UpdateTBLLoginUserDetailAuth (String authorization,int user_id);
//
//    @Query("DELETE FROM TBLLoginUserDetail")
//    void DeleteTBLLoginUserDetail();
//
//
//    //TBLUserDetail
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void addTBLUserDetail (TBLUserDetail tblUserDetail);
//
//    @Query("select * from TBLUserDetail")
//    List<TBLUserDetail> getTBLUserDetail();
//
//    @Query("DELETE FROM TBLUserDetail")
//    void DeleteTBLUserDetail();
//
//
//    //TBLAreaObjects
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void addTBLAreaObjects (TBLAreaObjects tblAreaObjects);
//
//    @Query("select * from TBLAreaObjects Order by area_object_id")
//    LiveData<List<TBLAreaObjects>> getLiveTBLAreaObjects();
//
//    @Query("select * from TBLAreaObjects Order by area_object_id")
//    List<TBLAreaObjects> getTBLAreaObjects();
//
//    @Query("select * from TBLAreaObjects where area_object_id = :AreaObjectId")
//    List<TBLAreaObjects> getTBLAreaObjectsOfArea(int AreaObjectId);
//
//    @Query("DELETE FROM TBLAreaObjects")
//    void DeleteTBLAreaObjects();
//
//
//    //TBLBoards
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void addTBLBoards (TBLBoards tblBoards);
//
//    @Query("select * from TBLBoards")
//    LiveData<List<TBLBoards>> getLiveTBLBoards();
//
//    @Query("select * from TBLBoards")
//    List<TBLBoards> getTBLBoards();
//
//    @Query("select * from TBLBoards where board_id = :BoardId")
//    List<TBLBoards> getBoardDetail(String BoardId);
//
//    @Query("select * from TBLBoards where board_assign_id_hex = :BoardIdHex")
//    List<TBLBoards> getBoardDetailForStatus (String BoardIdHex);
//
//    @Query("select * from TBLBoards where area_object_id = :AreaObjectId")
//    LiveData<List<TBLBoards>> getLiveBoardDetailForTopic(String AreaObjectId);
//
//    @Query("select * from TBLBoards where area_object_id = :AreaObjectId")
//    List<TBLBoards> getBoardDetailForTopic(String AreaObjectId);
//
//    @Query("update TBLBoards set status = :Status where board_assign_id_hex = :BoardId")
//    void addBoardStatus (String Status,String BoardId);
//
//    @Query("update TBLBoards set lock = :Lock, sleep = :Sleep, signal = :Signal where board_assign_id_hex = :BoardId")
//    void addBoardLockSleepSignal (String Lock,String Sleep, String Signal ,String BoardId);
//
//    @Query("select * from TBLBoards where mqtt_topic_name = :Topic")
//    List<TBLBoards> getBoardDetailForBoardId(String Topic);
//
//    @Query("DELETE FROM TBLBoards where board_id = :BoardId")
//    void DeleteTBLBoardsByBoardId(long BoardId);
//
//    @Query("DELETE FROM TBLBoards")
//    void DeleteTBLBoards();
//
//
//
//    //TBLEquipments
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void addTBLEquipments (TBLEquipments tblEquipments);
//
//    @Query("select * from TBLEquipments")
//    List<TBLEquipments> getTBLEquipments();
//
//    @Query("select * from TBLEquipments where area_object_id = :AreaObjectsId Order by equipment_id")
//    List<TBLEquipments> getEquipments(String AreaObjectsId);
//
//    @Query("select * from TBLEquipments where area_object_id = :AreaObjectsId Order by equipment_id")
//    LiveData<List<TBLEquipments>> getLiveEquipments(String AreaObjectsId);
//
//    @Query("select * from TBLEquipments where equipment_id = :EquipmentId")
//    List<TBLEquipments> getEquipmentsDetail(String EquipmentId);
//
//    @Query("update TBLEquipments set status = :Status where equipment_id = :EquipmentId")
//    void addEquipmentStatus (String Status,String EquipmentId);
//
//    @Query("update TBLEquipments set status = :Status,fanSpeed = :FanSpeed where equipment_id = :EquipmentId")
//    void addEquipmentStatusAndFanSpeed (String Status,String FanSpeed,String EquipmentId);
//
//    @Query("update TBLEquipments set status = :Status where equipment_switch_no = :SwitchNo AND board_assign_id_hex = :BoardHexId")
//    void addEquipmentStatusBySwitchAndBoardId(String Status, int SwitchNo , String BoardHexId);
//
//    @Query("update TBLEquipments set status = :Status,fanSpeed = :FanSpeed where equipment_switch_no = :Switch AND board_assign_id_hex = :BoardHexId")
//    void addEquipmentStatusBySwitchAndBoardIdForFan (String Status,String FanSpeed,int Switch,String BoardHexId);
//
//    @Query("select * from TBLEquipments where  board_assign_id_hex = :BoardHexId")
//    List<TBLEquipments> getEquipmentsDetailForStatus (String BoardHexId);
//
//    @Query("update TBLEquipments set equipment_type_name = :EquipmentTypeName, equipment_type_id = :EquipmentTypeId, equipment_alias_name = :EquipmentAliasName where equipment_id = :EquipmentId")
//    void UpdateEquipmentDetail (String EquipmentTypeName , int EquipmentTypeId,String EquipmentAliasName, String EquipmentId);
//
//    @Query("DELETE FROM TBLEquipments")
//    void DeleteTBLEquipments();
//
//
//    //TBLIrBlasters
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void addTBlIrBlasters (TBLIrBlasters tblIrBlasters);
//
//    @Query("select * from TBLIrBlasters")
//    List<TBLIrBlasters> getTBLIrBlasters();
//
//    @Query("select * from TBLIrBlasters where area_object_id = :AreaObjectId")
//    List<TBLIrBlasters> getBlasterOfArea(int AreaObjectId);
//
//    @Query("select * from TBLIrBlasters where ir_blaster_id = :IrBlasterId")
//    List<TBLIrBlasters> getTBLIrBlasterByBlasterId(long IrBlasterId);
//
//    @Query("DELETE FROM TBLIrBlasters where ir_blaster_id = :IrBlasterId")
//    void DeleteTBLIrBlastersByIrBlasterId(long IrBlasterId);
//
//    @Query("DELETE FROM TBLIrBlasters")
//    void DeleteTBLIrBlasters();
//
//
//    //TBLIrEquipments
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void addTBLIrEquipments (TBLIrEquipments tblIrEquipments);
//
//    @Query("select * from TBLIrEquipments")
//    List<TBLIrEquipments> getTBLIrEquipments();
//
//    @Query("select * from TBLIrEquipments where area_object_id = :AreaObjectsId Order by equipment_id")
//    LiveData<List<TBLIrEquipments>> getLiveIrEquipments(String AreaObjectsId);
//
//    @Query("select * from TBLIrEquipments where area_object_id = :AreaObjectsId Order by equipment_id")
//    List<TBLIrEquipments> getIrEquipments(String AreaObjectsId);
//
//    @Query("select * from TBLIrEquipments where equipment_id = :EquipmentId")
//    List<TBLIrEquipments> getIrEquipmentsForRemote(String  EquipmentId);
//
//    @Query("update TBLIrEquipments set status = :Status,lastCommand = :LastCommand where equipment_id = :EquipmentId")
//    void updateIrEquipmentsStatus (String Status,String LastCommand,String EquipmentId);
//
//    @Query("update TBLIrEquipments set equipment_alias_name = :NewName where equipment_id = :EquipmentId")
//    void updateIrEquipmentsName (String NewName,String EquipmentId);
//
//    @Query("DELETE FROM TBLIrEquipments")
//    void DeleteTBLIrEquipments();
//
//
//    //TBLIrAppliancesTypes
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void addTBLIrAppliancesTypes(TBLIrAppliancesTypes tblIrAppliancesTypes);
//
//    @Query("select * from TBLIrAppliancesTypes")
//    List<TBLIrAppliancesTypes> getTBLIrAppliancesTypes();
//
//    @Query("select * from TBLIrAppliancesTypes where type_full_name = :IrAppliancesTypeName")
//    List<TBLIrAppliancesTypes> getIrAppliancesTypeId(String IrAppliancesTypeName);
//
//    @Query("DELETE FROM TBLIrAppliancesTypes")
//    void DeleteTBLIrAppliancesTypes();
//
//
//    //TBLIrAppliancesBrands
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void addTBLIrAppliancesBrands(TBLIrAppliancesBrands tblIrAppliancesBrands);
//
//    @Query("select * from TBLIrAppliancesBrands")
//    List<TBLIrAppliancesBrands> getTBLIrAppliancesBrands();
//
//    @Query("DELETE FROM TBLIrAppliancesBrands")
//    void DeleteTBLIrAppliancesBrands();
//
//
//    //TBLIrAppliancesModels
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void addTBLIrAppliancesModels(TBLIrAppliancesModels tblIrAppliancesModels);
//
//    @Query("select * from TBLIrAppliancesModels")
//    List<TBLIrAppliancesModels> getTBLIrAppliancesModels();
//
//    @Query("select * from TBLIrAppliancesModels where type_id = :TypeId AND brand_id = :BrandId")
//    List<TBLIrAppliancesModels> getSelectedIrAppliances(int TypeId, int BrandId);
//
//    @Query("DELETE FROM TBLIrAppliancesModels")
//    void DeleteTBLIrAppliancesModels();
//
//
//    //TBLFBAcCommand
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void addTBLFBAcCommand (TBLFBAcCommand tblfbAcCommand);
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void addTBLFBAcCommandLastCommand (TBLFBAcCommand tblfbAcCommand);
//
//    @Query("select * from TBLFBAcCommand")
//    List<TBLFBAcCommand> getTBLFBAcCommand();
//
//    @Query("select * from TBLFBAcCommand where mode = :AcMode AND onOff = :OnOff AND temperature = :Temperature AND referenceCode = :ReferenceCode")
//    List<TBLFBAcCommand> getRemoteAcTBLFBACommand(String AcMode,String OnOff,int Temperature, String ReferenceCode);
//
//    @Query("select * from TBLFBAcCommand where  mode = :AcMode AND referenceCode = :ReferenceCode")
//    List<TBLFBAcCommand> getRemoteModeTBLFBACommand(String AcMode,String ReferenceCode);
//
//    @Query("select * from TBLFBAcCommand where swing = :Swing AND referenceCode = :ReferenceCode")
//    List<TBLFBAcCommand> getRemoteSwingTBLFBACommand(String Swing, String ReferenceCode);
//
//    @Query("select * from TBLFBAcCommand where fan = :FanSpeed AND mode = :AcMode AND referenceCode = :ReferenceCode")
//    List<TBLFBAcCommand> getRemoteFanSpeedTBLFBACommand(String FanSpeed,String AcMode,String ReferenceCode);
//
//    @Query("select * from TBLFBAcCommand where  onOff = :OnOff AND referenceCode = :ReferenceCode")
//    List<TBLFBAcCommand> getRemoteOffTBLFBACommand(String OnOff, String ReferenceCode);
//
//    @Query("select * from TBLFBAcCommand where  hexCode = :HexCode AND referenceCode = :ReferenceCode")
//    List<TBLFBAcCommand> getAcLastCommandTBLFBACommand(String HexCode, String ReferenceCode);
//
//    @Query("DELETE FROM TBLFBAcCommand")
//    void DeleteTBLFBAcCommand();
//
//
//    //TBLFBDvdCommand
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void addTBLFBDvdCommand (TBLFBDvdCommand tblfbDvdCommand);
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void addTBLFBDvdLastCommand (TBLFBDvdCommand tblfbDvdCommand);
//
//    @Query("select * from TBLFBDvdCommand")
//    List<TBLFBDvdCommand> getTBLFBDvdCommand();
//
//    @Query("select * from TBLFBDvdCommand where operation = :Operation AND referenceCode = :ReferenceCode")
//    List<TBLFBDvdCommand> getSelectedTBLFBDvdCommand(String Operation,String ReferenceCode);
//
//    @Query("select * from TBLFBDvdCommand where hexCode = :HexCode AND referenceCode = :ReferenceCode")
//    List<TBLFBDvdCommand> getDvdLastCommandTBLFBDvdCommand(String HexCode,String ReferenceCode);
//
//    @Query("DELETE FROM TBLFBDvdCommand")
//    void DeleteTBLFBDvdCommand();
//
//
//    //TBLFBFanRemoteCommand
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void addTBLFBFanRemoteCommand (TBLFBFanRemoteCommand tblfbFanRemoteCommand);
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void addTBLFBFanRemoteLastCommand (TBLFBFanRemoteCommand tblfbFanRemoteCommand);
//
//    @Query("select * from TBLFBFanRemoteCommand")
//    List<TBLFBFanRemoteCommand> getTBLFBFanRemoteCommand();
//
//    @Query("select * from TBLFBFanRemoteCommand where operation = :Operation AND referenceCode = :ReferenceCode")
//    List<TBLFBFanRemoteCommand> getSelectedTBLFBFanRemoteCommand(String Operation,String ReferenceCode);
//
//    @Query("select * from TBLFBFanRemoteCommand where hexCode = :HexCode AND referenceCode = :ReferenceCode")
//    List<TBLFBFanRemoteCommand> getRFanLastCommandTBLFBFanRemoteCommand(String HexCode,String ReferenceCode);
//
//    @Query("select * from TBLFBFanRemoteCommand where referenceCode = :ReferenceCode")
//    List<TBLFBFanRemoteCommand> getOptionalOperationTBLFBFanRemoteCommand(String ReferenceCode);
//
//    @Query("DELETE FROM TBLFBFanRemoteCommand")
//    void DeleteTBLFBFanRemoteCommand();
//
//
//    //TBLFBSetTopBoxCommand
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void addTBLFBSetTopBoxCommand (TBLFBSetTopBoxCommand tblfbSetTopBoxCommand);
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void addTBLFBSetTopBoxLastCommand (TBLFBSetTopBoxCommand tblfbSetTopBoxCommand);
//
//    @Query("select * from TBLFBSetTopBoxCommand")
//    List<TBLFBSetTopBoxCommand> getTBLFBSetTopBoxCommand();
//
//    @Query("select * from TBLFBSetTopBoxCommand where operation = :Operation AND referenceCode = :ReferenceCode")
//    List<TBLFBSetTopBoxCommand> getSelectedTBLFBSetTopBoxCommand(String Operation,String ReferenceCode);
//
//    @Query("select * from TBLFBSetTopBoxCommand where hexCode = :HexCode AND referenceCode = :ReferenceCode")
//    List<TBLFBSetTopBoxCommand> getStbLastCommandTBLFBSetTopBoxCommand(String HexCode,String ReferenceCode);
//
//    @Query("DELETE FROM TBLFBTelevisionCommand")
//    void DeleteTBLFBSetTopBoxCommand();
//
//
//    //TBLFBTelevisionCommand
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void addTBLFBTelevisionCommand (TBLFBTelevisionCommand tblfbTelevisionCommand);
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void addTBLFBTelevisionLastCommand (TBLFBTelevisionCommand tblfbTelevisionCommand);
//
//    @Query("select * from TBLFBTelevisionCommand")
//    List<TBLFBTelevisionCommand> getTBLFBTelevisionCommand();
//
//    @Query("select * from TBLFBTelevisionCommand where operation = :Operation AND referenceCode = :ReferenceCode")
//    List<TBLFBTelevisionCommand> getSelectedTBLFBTelevisionCommand(String Operation,String ReferenceCode);
//
//    @Query("select * from TBLFBTelevisionCommand where hexCode = :HexCode AND referenceCode = :ReferenceCode")
//    List<TBLFBTelevisionCommand> getTvLastCommandTBLFBTelevisionCommand(String HexCode,String ReferenceCode);
//
//    @Query("DELETE FROM TBLFBTelevisionCommand")
//    void DeleteTBLFBTelevisionCommand();
//
//
//
//    //TBLFBStbChannel
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void addTBLFBStbChannel(TBLFBStbChannel tblfbStbChannel);
//
//    @Query("select * from TBLFBStbChannel")
//    LiveData<List<TBLFBStbChannel>> getLiveTBLFBStbChannel();
//
//    @Query("select * from TBLFBStbChannel")
//    List<TBLFBStbChannel> getTBLFBStbChannel();
//
//    @Query("select * from TBLFBStbChannel where status = :Status")
//    LiveData<List<TBLFBStbChannel>> getRecentLiveTBLFBStbChannel(int Status);
//
//    @Query("select * from TBLFBStbChannel  where status = :Status")
//    List<TBLFBStbChannel> getRecentTBLFBStbChannel(int Status);
//
//    @Query("update TBLFBStbChannel set status = :Status where channel_name = :ChannelName")
//    void updateTBLFBStbChannelStatus (int Status,String ChannelName);
//
//    @Query("DELETE FROM TBLFBStbChannel")
//    void DeleteTBLFBStbChannel();
//
//
//
//    //TBLAcFrameHexCodeInfo
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void addTBLAcFrameHexCodeInfo(TBLAcFrameHexCodeInfo tblAcFrameHexCodeInfo);
//
//    @Query("select * from TBLAcFrameHexCodeInfo")
//    List<TBLAcFrameHexCodeInfo> getTBLAcFrameHexCodeInfo();
//
//    @Query("select * from TBLAcFrameHexCodeInfo where reference_code = :ReferenceCode")
//    List<TBLAcFrameHexCodeInfo> getTBLAcFrameHexCodeInfoForAc(String ReferenceCode);
//
//    @Query("DELETE FROM TBLAcFrameHexCodeInfo")
//    void DeleteTBLAcFrameHexCodeInfo();
//
//
//    //TBLMood
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void addTBLMood(TBLMood tblMood);
//
//    @Query("select * from TBLMood")
//    LiveData<List<TBLMood>> getLiveTBLMood();
//
//    @Query("select * from TBLMood")
//    List<TBLMood> getTBLMood();
//
//    @Query("delete from TBLMood Where `no` = :no")
//    void deleteTBLMood(int no);
//
//    @Query("select * from TBLMood Where area_object_id = :AreaId")
//    List<TBLMood> getTBLMoodDataByAreaId(String AreaId);
//
//    @Query("select * from TBLMood Where areaNameTopic = :AreaNameTopic")
//    List<TBLMood> getTBLMoodMoodNameTopic(String AreaNameTopic);
//
//    @Query("select * from TBLMood Where moodNameTopic = :MoodNameTopic")
//    List<TBLMood> getTBLMoodData(String MoodNameTopic);
//
//    @Query("DELETE FROM TBLMood")
//    void DeleteTBLMood();
//
//    //TBLAllEquipments
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void addTBLAllEquipments(TBLAllEquipments tblAllEquipments);
//
//    @Query("select * from TBLAllEquipments Order by equipment_id")
//    LiveData<List<TBLAllEquipments>> getLiveTBLAllEquipments();
//
//    @Query("select * from TBLAllEquipments Order by equipment_id")
//    List<TBLAllEquipments> getTBLAllEquipments();
//
//    @Query("DELETE FROM TBLAllEquipments")
//    void DeleteTBLAllEquipments();
//
//
//    //Board And Blaster
//    @Query("select * from TBLAllEquipments where area_object_id = :AreaId Order by reno")
//    List<TBLAllEquipments> getTBLAllEquipmentsByArea(long AreaId);
//
//    @Query("select * from TBLAllEquipments where area_object_id = :AreaId Order by reno")
//    LiveData<List<TBLAllEquipments>> getLiveTBLAllEquipmentsByArea(long AreaId);
//
//    @Query("select * from TBLAllEquipments where board_id = :BoardId AND board_type = :BoardType  Order by reno")
//    List<TBLAllEquipments> getTBLAllEquipmentsByBoardId(long BoardId , String BoardType);
//
//    @Query("select * from TBLAllEquipments where board_id = :BoardId AND board_type = :BoardType Order by reno")
//    LiveData<List<TBLAllEquipments>> getLiveTBLAllEquipmentsByBoardId(long BoardId , String BoardType);
//
//    @Query("select * from TBLAllEquipments where ir_blaster_id = :IrBlasterId AND board_type = :BoardType  Order by reno")
//    List<TBLAllEquipments> getTBLAllEquipmentsByIrBlasterId(long IrBlasterId , String BoardType);
//
//    @Query("select * from TBLAllEquipments where ir_blaster_id = :IrBlasterId AND board_type = :BoardType Order by reno")
//    LiveData<List<TBLAllEquipments>> getLiveTBLAllEquipmentsByIrBlasterId(long IrBlasterId , String BoardType);
//
//    @Query("select * from TBLAllEquipments where area_object_id = :AreaId Order by reno")
//    List<TBLAllEquipments> getTBLAllEquipmentsByBoard(long AreaId);
//
//    @Query("select * from TBLAllEquipments where area_object_id = :AreaId Order by reno")
//    LiveData<List<TBLAllEquipments>> getLiveTBLAllEquipmentsByBoard(long AreaId);
//
//    @Query("select * from TBLAllEquipments where equipment_id = :EquipmentId AND board_type = :BoardType")
//    List<TBLAllEquipments> getTBLAllEquipmentsDetail(String EquipmentId,String BoardType);
//
//    @Query("update TBLAllEquipments set status = :Status where equipment_id = :EquipmentId AND board_type = :BoardType")
//    void addTBLAllEquipmentsStatus (String Status,String EquipmentId,String BoardType);
//
//    @Query("update TBLAllEquipments set status = :Status,fanSpeed = :FanSpeed where equipment_id = :EquipmentId AND board_type = :BoardType")
//    void addTBLAllEquipmentsStatusAndFanSpeed (String Status,String FanSpeed,String EquipmentId,String BoardType);
//
//    @Query("update TBLAllEquipments set status = :Status where equipment_switch_no = :SwitchNo AND board_assign_id_hex = :BoardHexId")
//    void addTBLAllEquipmentsStatusBySwitchAndBoardId(String Status, int SwitchNo , String BoardHexId);
//
//    @Query("update TBLAllEquipments set status = :Status,FanSpeed = :FanSpeed where equipment_switch_no = :Switch AND board_assign_id_hex = :BoardHexId")
//    void addTBLAllEquipmentsStatusBySwitchAndBoardIdForFan (String Status,String FanSpeed,int Switch,String BoardHexId);
//
//    @Query("select * from TBLAllEquipments where  board_assign_id_hex = :BoardHexId")
//    List<TBLAllEquipments> getTBLAllEquipmentsDetailForStatus (String BoardHexId);
//
//    @Query("update TBLAllEquipments set equipment_alias_name = :NewName where equipment_id = :EquipmentId AND board_type = :BoardType")
//    void updateTBLAllEquipmentsNameForBlaster (String NewName,String EquipmentId,String BoardType);
//
//    @Query("update TBLAllEquipments set equipment_type_name = :EquipmentTypeName, equipment_type_id = :EquipmentTypeId, equipment_alias_name = :EquipmentAliasName where equipment_id = :EquipmentId AND board_type = :BoardType")
//    void UpdateTBLAllEquipmentsForBoard (String EquipmentTypeName , int EquipmentTypeId,String EquipmentAliasName, String EquipmentId,String BoardType);
//
//    @Query("update TBLAllEquipments set reno = :reno where equipment_id = :EquipmentId AND board_type = :BoardType")
//    void updateTBLAllEquipmentsReno (int reno,long EquipmentId,String BoardType);
}
