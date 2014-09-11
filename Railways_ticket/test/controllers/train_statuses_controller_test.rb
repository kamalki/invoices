require 'test_helper'

class TrainStatusesControllerTest < ActionController::TestCase
  setup do
    @train_status = train_statuses(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:train_statuses)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create train_status" do
    assert_difference('TrainStatus.count') do
      post :create, train_status: { Available_date: @train_status.Available_date, Available_seat1: @train_status.Available_seat1, Available_seat3: @train_status.Available_seat3, Avalable_seat2: @train_status.Avalable_seat2, Booked_seat1: @train_status.Booked_seat1, Booked_seat2: @train_status.Booked_seat2, Booked_seat3: @train_status.Booked_seat3, Train_id: @train_status.Train_id, Waiting_seat1: @train_status.Waiting_seat1, Waiting_seat2: @train_status.Waiting_seat2, Waiting_seat3: @train_status.Waiting_seat3 }
    end

    assert_redirected_to train_status_path(assigns(:train_status))
  end

  test "should show train_status" do
    get :show, id: @train_status
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @train_status
    assert_response :success
  end

  test "should update train_status" do
    patch :update, id: @train_status, train_status: { Available_date: @train_status.Available_date, Available_seat1: @train_status.Available_seat1, Available_seat3: @train_status.Available_seat3, Avalable_seat2: @train_status.Avalable_seat2, Booked_seat1: @train_status.Booked_seat1, Booked_seat2: @train_status.Booked_seat2, Booked_seat3: @train_status.Booked_seat3, Train_id: @train_status.Train_id, Waiting_seat1: @train_status.Waiting_seat1, Waiting_seat2: @train_status.Waiting_seat2, Waiting_seat3: @train_status.Waiting_seat3 }
    assert_redirected_to train_status_path(assigns(:train_status))
  end

  test "should destroy train_status" do
    assert_difference('TrainStatus.count', -1) do
      delete :destroy, id: @train_status
    end

    assert_redirected_to train_statuses_path
  end
end
