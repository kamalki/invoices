require 'test_helper'

class ConsistsOfsControllerTest < ActionController::TestCase
  setup do
    @consists_of = consists_ofs(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:consists_ofs)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create consists_of" do
    assert_difference('ConsistsOf.count') do
      post :create, consists_of: { station_ID: @consists_of.station_ID, stop_number: @consists_of.stop_number, train_ID: @consists_of.train_ID }
    end

    assert_redirected_to consists_of_path(assigns(:consists_of))
  end

  test "should show consists_of" do
    get :show, id: @consists_of
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @consists_of
    assert_response :success
  end

  test "should update consists_of" do
    patch :update, id: @consists_of, consists_of: { station_ID: @consists_of.station_ID, stop_number: @consists_of.stop_number, train_ID: @consists_of.train_ID }
    assert_redirected_to consists_of_path(assigns(:consists_of))
  end

  test "should destroy consists_of" do
    assert_difference('ConsistsOf.count', -1) do
      delete :destroy, id: @consists_of
    end

    assert_redirected_to consists_ofs_path
  end
end
