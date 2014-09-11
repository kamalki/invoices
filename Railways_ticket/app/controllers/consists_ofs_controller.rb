class ConsistsOfsController < ApplicationController
  before_action :set_consists_of, only: [:show, :edit, :update, :destroy]

  # GET /consists_ofs
  # GET /consists_ofs.json
  def index
    @consists_ofs = ConsistsOf.all
  end

  # GET /consists_ofs/1
  # GET /consists_ofs/1.json
  def show
  end

  # GET /consists_ofs/new
  def new
    @consists_of = ConsistsOf.new
  end

  # GET /consists_ofs/1/edit
  def edit
  end

  # POST /consists_ofs
  # POST /consists_ofs.json
  def create
    @consists_of = ConsistsOf.new(consists_of_params)

    respond_to do |format|
      if @consists_of.save
        format.html { redirect_to @consists_of, notice: 'Consists of was successfully created.' }
        format.json { render :show, status: :created, location: @consists_of }
      else
        format.html { render :new }
        format.json { render json: @consists_of.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /consists_ofs/1
  # PATCH/PUT /consists_ofs/1.json
  def update
    respond_to do |format|
      if @consists_of.update(consists_of_params)
        format.html { redirect_to @consists_of, notice: 'Consists of was successfully updated.' }
        format.json { render :show, status: :ok, location: @consists_of }
      else
        format.html { render :edit }
        format.json { render json: @consists_of.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /consists_ofs/1
  # DELETE /consists_ofs/1.json
  def destroy
    @consists_of.destroy
    respond_to do |format|
      format.html { redirect_to consists_ofs_url, notice: 'Consists of was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_consists_of
      @consists_of = ConsistsOf.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def consists_of_params
      params.require(:consists_of).permit(:station_ID, :train_ID, :stop_number)
    end
end
