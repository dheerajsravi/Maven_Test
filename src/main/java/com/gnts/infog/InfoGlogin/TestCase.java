 package com.gnts.infog.InfoGlogin;

import org.apache.poi.xssf.usermodel.XSSFSheet;



public class TestCase {
 
       static TestStatus status;
	public static void main(String[] args) {
		
		try {
			ExcelUtil input_test_cases = new ExcelUtil("Inventory.xlsx","Invoice");

			XSSFSheet excelWSheet = input_test_cases.getExcelWSheetObj();

			int lastRowNumber = excelWSheet.getLastRowNum();
			
			System.out.println("lastRowNumber"+lastRowNumber);
			
			int row = 1;

			for (; row <= lastRowNumber; row++) {
				System.out.println("" + input_test_cases.getCellData(row, 0));
				System.out.println("" + input_test_cases.getCellData(row, 4));

				String[] cellValue = null;

				switch (input_test_cases.getCellData(row, 0)) {

				case "WEB_BROWSER_INVOKE":
					
                    status=WebFunction.WEB_BROWSER_INVOKE(input_test_cases.getCellData(row, 4));
					input_test_cases.setCellData(" Firefox Browser launch Successfully", row, 6);
					input_test_cases.setCellData(status.getStatus(), row, 7);
					input_test_cases.setCellData(status.getErrormsg(), row, 8);
					break;

				case "WEB_BROWSER_NAVIGATE":
					// cellValue = input_test_cases.getCellData(row,
					// 1).split("\\|");

					WebFunction.WEB_BROWSER_NAVIGATE(input_test_cases.getCellData(row, 4));
					input_test_cases.setCellData(" Infog Url Navigated Successfully", row, 6);
					input_test_cases.setCellData(status.getStatus(), row, 7);
					input_test_cases.setCellData(status.getErrormsg(), row, 8);
					break;

				case "WEB_BROWSER_FULLSCREEN":
					// cellValue = input_test_cases.getCellData(row,
					// 1).split("\\|");

					WebFunction.WEB_BROWSER_FULLSCREEN();
					input_test_cases.setCellData("View The Fullscreen Mode", row, 6);
					input_test_cases.setCellData(status.getStatus(), row, 7);
					input_test_cases.setCellData(status.getErrormsg(), row, 8);
					break;

				case "WEB_ELEMENT_CLICK":
					cellValue = input_test_cases.getCellData(row, 1).split("\\|");
					status = WebFunction.WEB_ELEMENT_CLICK(cellValue[0], cellValue[1], cellValue[2]);
					// Status
					input_test_cases.setCellData(status.getStatus(), row, 7);
					if (status.getStatus().equals("PASS")) {
						// Actual Value
						input_test_cases.setCellData(cellValue[2], row, 6);
					} else if (status.getStatus().equals("FAIL")) {
						// Message
						input_test_cases.setCellData(status.getErrormsg(), row, 8);

					}

					break;

				case "WEB_EDIT_TYPE":
					cellValue = input_test_cases.getCellData(row, 1).split("\\|");
				    status = WebFunction.WEB_EDIT_TYPE(cellValue[0], cellValue[1],input_test_cases.getCellData(row, 4), cellValue[2]);

					// Status
					input_test_cases.setCellData(status.getStatus(), row, 7);

					if (status.getStatus().equals("PASS")) {
						// Actual Value
						input_test_cases.setCellData(cellValue[2], row, 6);
						
					} else if (status.getStatus().equals("FAIL")) {
						
						// Message
						input_test_cases.setCellData(status.getErrormsg(), row,10);

					}

					break;

				case "LOGIN_FUNCTION":

					cellValue = input_test_cases.getCellData(row, 1).split("\\|");
					status = WebFunction.LOGIN_FUNCTION(cellValue);
					// Status
					input_test_cases.setCellData(status.getStatus(), row, 7);

					if (status.getStatus().equals("PASS")) {
						// Actual Value
						input_test_cases.setCellData(status.getErrormsg(), row, 6);

						break;
					}
					break;
				
				case "SIGNUP_DROPDOWN_SELECT":

					cellValue = input_test_cases.getCellData(row, 1).split("\\|");
					status = WebFunction.SIGNUP_DROPDOWN_SELECT(cellValue[0],input_test_cases.getCellData(row, 4));
					// Status
					input_test_cases.setCellData(status.getStatus(), row, 7);

					if (status.getStatus().equals("PASS")) {
						// Actual Value
						input_test_cases.setCellData(status.getErrormsg(), row, 6);

						break;
					} else if (status.getStatus().equals("FAIL")) {
						
						// Message
						input_test_cases.setCellData(status.getErrormsg(), row, 8);

					}
					break;
				case "SIGNUP_FUNCTION":

					cellValue = input_test_cases.getCellData(row, 1).split("\\|");
					TestStatus status5 = WebFunction.SIGNUP_FUNCTION(cellValue);
					// Status
					input_test_cases.setCellData(status5.getStatus(), row, 7);

					if (status5.getStatus().equals("PASS")) {
						// Actual Value
						input_test_cases.setCellData(status5.getErrormsg(), row, 6);

						break;
					}
					break;

				
				case "INVOICE_SUBMIT":

					cellValue = input_test_cases.getCellData(row, 1).split("\\|");
					TestStatus status14 = WebFunction.INVOICE_SUBMIT(cellValue);
					// Status
					input_test_cases.setCellData(status14.getStatus(), row, 7);

					if (status14.getStatus().equals("PASS")) {
						// Actual Value
						input_test_cases.setCellData(status14.getElement(), row, 4);
						input_test_cases.setCellData(status14.getErrormsg(), row, 6);

						break;

					}
					break;
				case "THREADSLEEP":

					// cellValue = input_test_cases.getCellData(row,
					// 1).split("\\|");
					status = WebFunction.THREADSLEEP();
					// Status
					input_test_cases.setCellData(status.getStatus(), row, 7);

					if (status.getStatus().equals("PASS")) {
						// Actual Value
						input_test_cases.setCellData(status.getErrormsg(), row, 6);

						break;
					}
					
					
				   case "INVOICE_NAME_SELECT":
					   
					   //  cellValue = input_test_cases.getCellData(row, 1).split("\\|");
						TestStatus status12 = WebFunction.INVOICE_NAME_SELECT(input_test_cases.getCellData(row, 4));
						// Status
						input_test_cases.setCellData(status12.getStatus(), row, 7);
						if (status12.getStatus().equals("PASS")) {
							// Actual Value
							input_test_cases.setCellData("System selected the customer name", row, 6);
						} else  {
							// Message
							input_test_cases.setCellData(status12.getErrormsg(), row, 8);

						}
						
						break;
						
					case "INVOICE_DROPDOWN":

						cellValue = input_test_cases.getCellData(row, 1).split("\\|");
						TestStatus status13 =WebFunction.INVOICE_DROPDOWN(cellValue[0],input_test_cases.getCellData(row, 4));
						// Status
						input_test_cases.setCellData(status13.getStatus(), row, 7);

						if (status13.getStatus().equals("PASS")) {
							// Actual Valuell
							input_test_cases.setCellData(cellValue[1], row, 6);

							
						} else  {
							
							// Message
							input_test_cases.setCellData(status13.getErrormsg(), row, 8);
						}
       
						
						break;
											
					case "WEB_GET_CELL_DATA":

						cellValue = input_test_cases.getCellData(row, 1).split("\\|");
						status = WebFunction.WEB_GET_CELL_DATA(input_test_cases.getCellData(row, 4),cellValue[0]);
						// Status
						input_test_cases.setCellData(status.getStatus(), row, 7);

						if (status.getStatus().equals("PASS")) {
							// Actual Value
							//input_test_cases.setCellData(status15.getElement(), row, 4);
							input_test_cases.setCellData(status.getErrormsg(), row, 6);
							break;

						}
						else{
							input_test_cases.setCellData(status.getErrormsg(), row, 8);
						}
						break;
					case "EDIT_BRANDS":

						//cellValue = input_test_cases.getCellData(row, 1).split("\\|");
						status = WebFunction.EDIT_BRANDS();
						// Status
						input_test_cases.setCellData(status.getStatus(), row, 7);

						if (status.getStatus().equals("PASS")) {
							// Actual Value
							//input_test_cases.setCellData(status15.getElement(), row, 4);
							input_test_cases.setCellData(status.getErrormsg(), row, 6);
						}
							else{
								input_test_cases.setCellData(status.getErrormsg(), row, 8);
							}

						
						break;
						
					case "BRANDS_SAVE":

						cellValue = input_test_cases.getCellData(row, 1).split("\\|");
						TestStatus status18 = WebFunction.BRANDS_SAVE(cellValue);
						// Status
						input_test_cases.setCellData(status18.getStatus(), row, 7);

						if (status18.getStatus().equals("PASS")) {
							// Actual Value
							input_test_cases.setCellData(status18.getStatus_message(), row, 6);

							break;
						}
						else if(status18.getStatus().equals("FAIL")){
							input_test_cases.setCellData(status18.getErrormsg(), row, 8);
						}
						break;
						
						case "CATEGORY_SAVE":

						cellValue = input_test_cases.getCellData(row, 1).split("\\|");
						TestStatus status20 = WebFunction.CATEGORY_SAVE(cellValue);
						// Status
						input_test_cases.setCellData(status20.getStatus(), row, 7);

						if (status20.getStatus().equals("PASS")) {
							// Actual Value
							//input_test_cases.setCellData(status15.getElement(), row, 4);
							input_test_cases.setCellData(status20.getErrormsg(), row, 6);

							break;

						}
						break;
					case "EDIT_CATEGORY":

						//cellValue = input_test_cases.getCellData(row, 1).split("\\|");
						TestStatus status21 = WebFunction.EDIT_CATEGORY();
						// Status
						input_test_cases.setCellData(status21.getStatus(), row, 7);

						if (status21.getStatus().equals("PASS")) {
							// Actual Value
							//input_test_cases.setCellData(status15.getElement(), row, 4);
							input_test_cases.setCellData(status21.getErrormsg(), row, 6);
							break;

						}
						break;
					case "SELECT_UOM":

						cellValue = input_test_cases.getCellData(row, 1).split("\\|");
						TestStatus status22 = WebFunction.SELECT_UOM(cellValue[0],input_test_cases.getCellData(row, 4),cellValue[1]);
						// Status
						input_test_cases.setCellData(status22.getStatus(), row, 7);

						if (status22.getStatus().equals("PASS")) {
							// Actual Value
							//input_test_cases.setCellData(status15.getElement(), row, 4);
							input_test_cases.setCellData(cellValue[1], row, 6);
							break;

						}
						break;
						
					case "PRODUCT_SAVE":

						cellValue = input_test_cases.getCellData(row, 1).split("\\|");
						TestStatus status23 = WebFunction.PRODUCT_SAVE(cellValue);
						// Status
						input_test_cases.setCellData(status23.getStatus(), row, 7);

						if (status23.getStatus().equals("PASS")) {
							// Actual Value
							//input_test_cases.setCellData(status15.getElement(), row, 4);
							input_test_cases.setCellData(status23.getErrormsg(), row, 8);

						}
							else{
								input_test_cases.setCellData(status23.getErrormsg(), row, 8);
							}

						break;
						
					
						
				
			case "VIEW_PRODUCT":

				cellValue = input_test_cases.getCellData(row, 1).split("\\|");
				TestStatus status24 = WebFunction.VIEW_PRODUCT(input_test_cases.getCellData(row, 4),cellValue[0]);
				// Status
				input_test_cases.setCellData(status24.getStatus(), row, 7);

				if (status24.getStatus().equals("PASS")) {
					// Actual Value
					//input_test_cases.setCellData(status15.getElement(), row, 4);
					input_test_cases.setCellData(status24.getErrormsg(), row, 6);
					
			
				}
				break;	
			case "PRODUCT_UPDATE":

				cellValue = input_test_cases.getCellData(row, 1).split("\\|");
				TestStatus status25 = WebFunction.PRODUCT_UPDATE(cellValue);
				// Status
				input_test_cases.setCellData(status25.getStatus(), row, 7);

				if (status25.getStatus().equals("PASS")) {
					
					input_test_cases.setCellData(status25.getErrormsg(), row, 6);

				}
					else{
						input_test_cases.setCellData(status25.getErrormsg(), row, 8);
					}

				break;
				
			case "LEDGER_CREATE_DROPDOWN":

				cellValue = input_test_cases.getCellData(row, 1).split("\\|");
			    status = WebFunction.LEDGER_CREATE_DROPDOWN(cellValue[0],input_test_cases.getCellData(row, 4),cellValue[1]);
				// Status
				input_test_cases.setCellData(status.getStatus(), row, 7);

				if (status.getStatus().equals("PASS")) {
					// Actual Value
					
					input_test_cases.setCellData(cellValue[1], row, 6);
					

				}
				else{
					input_test_cases.setCellData(status.getErrormsg(), row, 8);
				}

			
				break;
				
			case "LEDGER_ADD_SAVE":

				cellValue = input_test_cases.getCellData(row, 1).split("\\|");
				status= WebFunction.LEDGER_ADD_SAVE(cellValue);
				// Status
				input_test_cases.setCellData(status.getStatus(), row, 7);

				if (status.getStatus().equals("PASS")) {
					// Actual Value
		
					input_test_cases.setCellData(status.getErrormsg(), row, 6);
					break;
					}
				else if(status.getStatus().equals("FAIL")){
					input_test_cases.setCellData(status.getErrormsg(), row, 8);
				}
				break;	
				
			case "LEDGER_EDIT_SAVE":

				cellValue = input_test_cases.getCellData(row, 1).split("\\|");
				status= WebFunction.LEDGER_EDIT_SAVE(cellValue);
				// Status
				input_test_cases.setCellData(status.getStatus(), row, 7);

				if (status.getStatus().equals("PASS")) {
					// Actual Value
		
					input_test_cases.setCellData(status.getErrormsg(), row, 6);
					break;
					}
				else if(status.getStatus().equals("FAIL")){
					input_test_cases.setCellData(status.getErrormsg(), row, 8);
				}
				break;	
				
				
				
			case "VIEW_LEDGER":

				cellValue = input_test_cases.getCellData(row, 1).split("\\|");
				TestStatus status28 = WebFunction.VIEW_LEDGER(input_test_cases.getCellData(row, 4),cellValue[0]);
				// Status
				input_test_cases.setCellData(status28.getStatus(), row, 7);

				if (status28.getStatus().equals("PASS")) {
					// Actual Value
					
					input_test_cases.setCellData(cellValue[0], row, 6);
					}
				else if (status.getStatus().equals("FAIL")) {
					
					// Message
					input_test_cases.setCellData(status.getErrormsg(), row, 8);

				}
				break;
				
			case "WEB_PICDATE":

				cellValue = input_test_cases.getCellData(row, 1).split("\\|");
				TestStatus status29 = WebFunction.WEB_PICDATE(cellValue[0],cellValue[1],cellValue[2],input_test_cases.getCellData(row, 4));
				// Status
				input_test_cases.setCellData(status29.getStatus(), row, 7);

				if (status29.getStatus().equals("PASS")) {
					// Actual Value
					input_test_cases.setCellData(status29.getErrormsg(), row, 6);

					break;
				}
				break;
				
			case "INVOICE_DELETE":

				cellValue = input_test_cases.getCellData(row, 1).split("\\|");
				TestStatus status30 = WebFunction.INVOICE_DELETE(input_test_cases.getCellData(row, 4),cellValue[0]);
				// Status
				input_test_cases.setCellData(status30.getStatus(), row, 7);

				if (status30.getStatus().equals("PASS")) {
					// Actual Value
					input_test_cases.setCellData(status30.getElement(), row, 4);
					input_test_cases.setCellData(status30.getErrormsg(), row, 6);
					
			
				}
				break;	
				
			case "AddNEW_PRODUCT_ROW":

				//cellValue = input_test_cases.getCellData(row, 1).split("\\|");
				TestStatus status31 = WebFunction.AddNEW_PRODUCT_ROW();
				// Status
				input_test_cases.setCellData(status31.getStatus(), row, 7);

				if (status31.getStatus().equals("PASS")) {
					// Actual Value
					//input_test_cases.setCellData(status15.getElement(), row, 4);
					input_test_cases.setCellData(status31.getErrormsg(), row, 6);
					break;

				}
				break;
				
			case "INVOICE_FULLSCREENMODE":

				//cellValue = input_test_cases.getCellData(row, 1).split("\\|");
				TestStatus status32 = WebFunction.INVOICE_FULLSCREENMODE();
				// Status
				input_test_cases.setCellData(status32.getStatus(), row, 7);

				if (status32.getStatus().equals("PASS")) {
					// Actual Value
					//input_test_cases.setCellData(status15.getElement(), row, 4);
					input_test_cases.setCellData(status32.getErrormsg(), row, 6);
					break;

				}
				break;
				
			case "INVOICE_CANCEL":

				//cellValue = input_test_cases.getCellData(row, 1).split("\\|");
				TestStatus status33 = WebFunction.INVOICE_CANCEL();
				// Status
				input_test_cases.setCellData(status33.getStatus(), row, 7);

				if (status33.getStatus().equals("PASS")) {
					// Actual Value
					//input_test_cases.setCellData(status15.getElement(), row, 4);
					input_test_cases.setCellData(status33.getErrormsg(), row, 6);
					break;

				}
				break;
			case "VIEW_INVOICE":

				//cellValue = input_test_cases.getCellData(row, 1).split("\\|");
				TestStatus status34 = WebFunction.VIEW_INVOICE(input_test_cases.getCellData(row, 4));
				// Status
				input_test_cases.setCellData(status34.getStatus(), row, 7);

				if (status34.getStatus().equals("PASS")) {
					// Actual Value
					input_test_cases.setCellData(status34.getElement(), row, 3);
					input_test_cases.setCellData(status34.getErrormsg(), row, 6);
					
			
				}
				break;	
				
			case "RECIEPT_NAME_SELECT":

				//cellValue = input_test_cases.getCellData(row, 1).split("\\|");
				TestStatus status35 = WebFunction.RECIEPT_NAME_SELECT(input_test_cases.getCellData(row, 4));
				// Status
				input_test_cases.setCellData(status35.getStatus(), row, 7);

				if (status35.getStatus().equals("PASS")) {
					// Actual Value
					
					input_test_cases.setCellData(status35.getErrormsg(), row, 6);
					
			
				}
				break;	
				
			case "RECEIPT_MODE":

				//cellValue = input_test_cases.getCellData(row, 1).split("\\|");
				TestStatus status36 = WebFunction.RECEIPT_MODE();
				// Status
				input_test_cases.setCellData(status36.getStatus(), row, 7);

				if (status36.getStatus().equals("PASS")) {
					// Actual Value
					
					input_test_cases.setCellData(status36.getErrormsg(), row, 6);
					
			
				}
				break;
				
			case "RECIEPT_SAVE":

				cellValue = input_test_cases.getCellData(row, 1).split("\\|");
				TestStatus status37 = WebFunction.RECIEPT_SAVE(cellValue);
				// Status
				input_test_cases.setCellData(status37.getStatus(), row, 7);

				if (status37.getStatus().equals("PASS")) {
					// Actual Value
					input_test_cases.setCellData(status37.getElement(), row, 4);
					input_test_cases.setCellData(status37.getErrormsg(), row, 6);

					break;

				}
				break;
				
				
			case "RECEIPT_ADDROW":

				//cellValue = input_test_cases.getCellData(row, 1).split("\\|");
				TestStatus status38 = WebFunction.RECEIPT_ADDROW();
				// Status
				input_test_cases.setCellData(status38.getStatus(), row, 7);

				if (status38.getStatus().equals("PASS")) {
					// Actual Value
					//input_test_cases.setCellData(status15.getElement(), row, 4);
					input_test_cases.setCellData(status38.getErrormsg(), row, 6);
					break;

				}
				break;
				
			case "RECEIPT_FULLSCREENMODE":

				//cellValue = input_test_cases.getCellData(row, 1).split("\\|");
				TestStatus status39 = WebFunction.RECEIPT_FULLSCREENMODE();
				// Status
				input_test_cases.setCellData(status39.getStatus(), row, 7);

				if (status39.getStatus().equals("PASS")) {
					// Actual Value
					//input_test_cases.setCellData(status15.getElement(), row, 4);
					input_test_cases.setCellData(status39.getErrormsg(), row, 6);
					break;

				}
				break;
				
			case "RECEIPT_CANCEL":

				//cellValue = input_test_cases.getCellData(row, 1).split("\\|");
				TestStatus status40 = WebFunction.RECEIPT_CANCEL();
				// Status
				input_test_cases.setCellData(status40.getStatus(), row, 7);

				if (status40.getStatus().equals("PASS")) {
					// Actual Value
					//input_test_cases.setCellData(status15.getElement(), row, 4);
					input_test_cases.setCellData(status40.getErrormsg(), row, 6);
					break;

				}
				break;
						
			case "PAGE_UP":

				//cellValue = input_test_cases.getCellData(row, 1).split("\\|");
				 status = WebFunction.PAGE_UP();
				// Status
				input_test_cases.setCellData(status.getStatus(), row, 7);

				if (status.getStatus().equals("PASS")) {
					// Actual Value
					
					input_test_cases.setCellData(status.getStatus_message(), row, 6);
					break;

				}
				break;
				}
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
