弹框选择器

目前包括：三级联动地址选择器 和 预约上门收件时间选择器

用法：
1、地址选择器
        AddressPicker addressPicker = new AddressPicker(MainActivity.this);
        addressPicker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
            @Override
            public void onAddressPicked(Province province, City city, County county) {
                String addr = province.getName() + "   " + city.getName() + "   " + county
                    .getName();
                address.setText(addr);
            }
        });
        addressPicker.setSelectedItem("广东省", "广州市", "天河区");//指定默认选中项，可不设置
        addressPicker.show();

2、时间选择器
        BookingTimePicker bookingTimePicker = new BookingTimePicker(this, new BookingTimePicker
            .OnBookingTimePickedListener() {
            @Override
            public void onBookingTimePicked(String label, String bookingTime) {
                address.setText(label);
            }
        });
        bookingTimePicker.show();