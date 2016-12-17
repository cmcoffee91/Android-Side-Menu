/*
 *	Copyright 2015, Christopher Coffee
 *
 *	All rights reserved.
 *
 *	Redistribution and use in source and binary forms, with or without modification, are 
 *	permitted provided that the following conditions are met:
 *
 *	Redistributions of source code must retain the above copyright notice which includes the
 *	name(s) of the copyright holders. It must also retain this list of conditions and the 
 *	following disclaimer. 
 *
 *	Redistributions in binary form must reproduce the above copyright notice, this list 
 *	of conditions and the following disclaimer in the documentation and/or other materials 
 *	provided with the distribution. 
 *
 *	Neither the name of David Book, or buzztouch.com nor the names of its contributors 
 *	may be used to endorse or promote products derived from this software without specific 
 *	prior written permission.
 *
 *	THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
 *	ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 *	WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
 *	IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
 *	INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
 *	NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 *	PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 *	WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 *	ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY 
 *	OF SUCH DAMAGE. 
 */
package com.buzzTouch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CM_droidSideMenu extends BT_fragment{

	//onCreateView...
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState){
	       
		/*
			Note: fragmentName property is already setup in the parent class (BT_fragment). This allows us 
			to add the 	name of this class file to the LogCat console using the BT_debugger.
		*/
		//show life-cycle event in LogCat console...
		BT_debugger.showIt(fragmentName + ":onCreateView JSON itemId: \"" + screenData.getItemId() + "\" itemType: \"" + screenData.getItemType() + "\" itemNickname: \"" + screenData.getItemNickname() + "\"");
	
		//inflate the layout file for this screen...
		View thisScreensView = inflater.inflate(R.layout.cm_droidsidemenu, container, false);

		//show a long toast so user knows this screen is blank intentionally...
		showToast("This screen is blank intentionally. See CM_droidSideMenu.java to see how little is required to launch a simple plugin.", "long");
		
        //return the layout file as the view for this screen..
        return thisScreensView;      
        
 		
	}//onCreateView...


}


 


