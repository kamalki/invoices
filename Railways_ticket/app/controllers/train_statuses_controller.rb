class TrainStatusesController < ApplicationController
  before_action :set_train_status, only: [:show, :edit, :update, :destroy]

  # GET /train_statuses
  # GET /train_statuses.json
  def index
    @train_statuses = TrainStatus.all
  end

  # GET /train_statuses/1
  # GET /train_statuses/1.json
  def show
  end

  # GET /train_statuses/new
  def new
    @train_status = TrainStatus.new
  end

  # GET /train_statuses/1/edit
  def edit
  end

  # POST /train_statuses
  # POST /train_statuses.json
  def create
    @train_status = TrainStatus.new(train_status_params)

    respond_to do |format|
      if @train_status.save
        format.html { redirect_to @train_status, notice: 'Train status was successfully created.' }
        format.json { render :show, status: :created, location: @train_status }
      else
        format.html { render :new }
        format.json { render json: @train_status.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /train_statuses/1
  # PATCH/PUT /train_statuses/1.json
  def update
    respond_to do |format|
      if @train_status.update(train_status_params)
        format.html { redirect_to @train_status, notice: 'Train status was successfully updated.' }
        format.json { render :show, status: :ok, location: @train_status }
      else
        format.html { render :edit }
        format.json { render json: @train_status.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /train_statuses/1
  # DELETE /train_statuses/1.json
  def destroy
    @train_status.destroy
    respond_to do |format|
      format.html { redirect_to train_statuses_url, notice: 'Train status was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_train_status
      @train_status = TrainStatus.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def train_status_params
      params.require(:train_status).permit(:Train_id, :Available_date, :Booked_seat1, :Booked_seat2, :Booked_seat3, :Waiting_seat1, :Waiting_seat2, :Waiting_seat3, :Available_seat1, :Avalable_seat2, :Available_seat3)
    end
end
